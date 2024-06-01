package pies.FastbuilderCustomGUI;

import cf.pies.fastbuilder.api.gui.GUI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pies.FastbuilderCustomGUI.Util.ItemBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomGUI extends GUI {
    public boolean isBound;
    public String name;
    public CustomGUI(String name, boolean bound, InventoryHolder holder) {
        this.name = name;
        this.isBound = bound;
        this.holder = holder;
    }
    public InventoryHolder holder;

    @Override
    public InventoryHolder getHolder() {
        return holder;
    }

    @Override
    public Inventory getNewInventory(Player player) {
        Inventory i = Bukkit.createInventory(holder, Main.instance.getConfig().getInt("gui." + this.name + ".size") * 9, Main.instance.getConfig().getString("gui." + this.name + ".name").replace("&", "§"));

        for (String key : Main.instance.getConfig().getConfigurationSection("gui." + this.name + ".items").getKeys(false)) {
            int slot = Integer.parseInt(key);

            String name = Main.instance.getConfig().getString("gui." + this.name + ".items." + key + ".name");
            String item = Main.instance.getConfig().getString("gui." + this.name + ".items." + key + ".item");
            String lore = Main.instance.getConfig().getString("gui." + this.name + ".items." + key + ".lore");
            int amount = Main.instance.getConfig().contains("gui." + this.name + ".items." + key + ".amount") ?
                    Main.instance.getConfig().getInt("gui." + this.name + ".items." + key + ".amount") : 1;

            String[] itemSplit = item.split(":");

            Material material = Material.matchMaterial(itemSplit[0]);
            if (material == null) {
                player.sendMessage("Unknown material " + itemSplit[0]);
                continue;
            }
            ItemBuilder builder = new ItemBuilder(material);
            builder.durability(Integer.parseInt(itemSplit[1]));
            builder.amount(amount);

            if (lore != null) {
                List<String> loreList = Arrays.asList(lore.replace("&", "§").split("\\\\n|\n"));
                if (Main.isPlaceholderAPIInstalled()) {
                    loreList = loreList.stream()
                            .map(str -> PlaceholderAPI.setPlaceholders(player, str))
                            .collect(Collectors.toList());
                }
                builder.lore(loreList);
            }
            if (name != null) {
                builder.name(name.replace("&", "§"));
                if (Main.isPlaceholderAPIInstalled()) {
                    name = PlaceholderAPI.setPlaceholders(player, name);
                }
            }

            i.setItem(slot, builder
                    .hideAttr()
                    .hidePotion()
                    .hideEnch()
                    .build());
        }

        if (Main.instance.getConfig().contains("gui." + this.name + ".fill")) {
            for (int j = 0; j < Main.instance.getConfig().getInt("gui." + this.name + ".size") * 9; j++) {
                if (!(i.getItem(j) == null || i.getItem(j).getType() == Material.AIR)) continue;
                String item = Main.instance.getConfig().getString("gui." + this.name + ".fill");
                Material material = Material.matchMaterial(item.split(":")[0]);
                int type = Integer.parseInt(item.split(":")[1]);
                i.setItem(j, new ItemBuilder(material).durability(type).name("§f").build());
            }
        }
        return i;
    }

    @Override
    public void clickEvent(Player player, InventoryClickEvent event) {
        List<String> actions = Main.instance.getConfig().getStringList("gui." + this.name + ".items." + event.getRawSlot() + ".actions");
        for (String action : actions) {
            Main.actionManager.run(action, event);
        }
    }
}

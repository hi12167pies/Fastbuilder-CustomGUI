package pies.FastbuilderCustomGUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pies.FastbuilderAPI.GUI;
import top.speedcubing.lib.bukkit.inventory.ItemBuilder;

import java.util.List;

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

            ItemBuilder builder = new ItemBuilder(Material.matchMaterial(itemSplit[0]));
            builder.durability(Integer.parseInt(itemSplit[1]));
            builder.amount(amount);

            if (lore != null)
                builder.lore(lore.replace("&", "§"));
            if (name != null)
                builder.name(name.replace("&", "§"));

            i.setItem(slot, builder.build());
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

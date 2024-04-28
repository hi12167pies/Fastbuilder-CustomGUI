package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderAPI.FBManager;
import pies.FastbuilderAPI.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class BlockAction implements Action {
    FBManager manager = FastbuilderProvider.getApi().getManager();

    @Override
    public String getName() {
        return "block";
    }

    @Override
    public void executeAction(InventoryClickEvent e, List<String> args, String cmd) {
        Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        Material m = Material.matchMaterial(args.get(1).split(":")[0]);
        int price = Integer.parseInt(args.get(2));
        byte type = (byte) Integer.parseInt(args.get(1).split(":")[1]);
        String block = m + ":" + type;

        if (price == 0 || manager.hasBlock(player, m, type)) {
            manager.selectBlock(player, m, type);
            manager.resetPlayerInventory(player);
            return;
        }

        if (manager.getCurrency(player.getUniqueId()) >= price) {
            manager.addCurrency(player.getUniqueId(), price * -1);
            manager.buyBlock(player, m, type);
            player.sendMessage("§aYou now own " + block);
        } else {
            player.sendMessage(manager.getPrefix() + "§cYou do not have enough "+ manager.getConfig().getString("currency.display.e") +" to buy that.");
        }
    }
}

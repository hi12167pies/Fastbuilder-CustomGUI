package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import org.bukkit.Material;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class BlockAction implements Action {
    @Override
    public String getName() {
        return "block";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        Material material = Material.matchMaterial(args.get(1).split(":")[0]);
        int price = Integer.parseInt(args.get(2));
        byte type = (byte) Integer.parseInt(args.get(1).split(":")[1]);
        String block = material + ":" + type;

        if (price == 0 || player.isOwnBlock(material, type)) {
            player.setSelectedBlock(material, type);
            player.resetInventory();
            return;
        }

        if (player.getCurrency() >= price) {
            player.addCurrency(price * -1);
            player.buyBlock(material, type);
            player.getPlayer().sendMessage("§aYou now own " + block);
        } else {
            player.getPlayer().sendMessage("§cYou do not have enough currency to buy that.");
        }
    }
}

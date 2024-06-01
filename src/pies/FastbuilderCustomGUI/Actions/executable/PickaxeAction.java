package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import org.bukkit.Material;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class PickaxeAction implements Action {
    @Override
    public String getName() {
        return "pick";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        Material material = Material.matchMaterial(args.get(1).split(":")[0]);
        player.setPickaxe(material);
        player.resetInventory();
    }
}

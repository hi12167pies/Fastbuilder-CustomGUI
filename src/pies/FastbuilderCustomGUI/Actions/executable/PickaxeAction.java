package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderAPI.FBManager;
import pies.FastbuilderAPI.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class PickaxeAction implements Action {
    FBManager manager = FastbuilderProvider.getApi().getManager();
    @Override
    public String getName() {
        return "pick";
    }

    @Override
    public void executeAction(InventoryClickEvent e, List<String> args, String cmd) {
        Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        Material m = Material.matchMaterial(args.get(1).split(":")[0]);
        manager.setPickaxe(player, m);
        manager.resetPlayerInventory(player);
    }
}

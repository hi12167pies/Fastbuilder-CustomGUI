package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderAPI.FBManager;
import pies.FastbuilderAPI.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class StatsAction implements Action {
    FBManager manager = FastbuilderProvider.getApi().getManager();
    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public void executeAction(InventoryClickEvent event, List<String> args, String cmd) {
        if (args.get(1).equals("resetpb")) {
            Player player = (Player) event.getWhoClicked();
            manager.setPersonalBest(player.getUniqueId(), manager.getCurrentMode(player), 0);
        } else {
            event.getWhoClicked().sendMessage("action not found: stats." + args.get(1));
        }
    }
}

package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderAPI.FBManager;
import pies.FastbuilderAPI.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class ModeAction implements Action {
    FBManager manager = FastbuilderProvider.getApi().getManager();
    @Override
    public String getName() {
        return "mode";
    }

    @Override
    public void executeAction(InventoryClickEvent event, List<String> args, String cmd) {
        Player player = (Player) event.getWhoClicked();
        String mode = args.get(1);

        if (manager.getNextValidArena(mode) == null) {
            player.sendMessage(manager.getMessage("mode_full"));
            return;
        }
        if (manager.isReplayExist(player) && manager.isWatchingReplay(player)) {
            player.sendMessage(manager.getMessage("cannot_change_mode_in_replay"));
            return;
        }
        if (mode.equals(manager.getCurrentMode(player))) {
            player.sendMessage(manager.getMessage("already_in_mode"));
            return;
        }
        manager.leave(player, true, false, false);
        manager.join(player, mode);
    }
}

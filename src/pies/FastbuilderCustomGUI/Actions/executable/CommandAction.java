package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class CommandAction implements Action {
    @Override
    public String getName() {
        return "cmd";
    }

    @Override
    public void executeAction(InventoryClickEvent event, List<String> args, String cmd) {
        Player player = (Player) event.getWhoClicked();
        player.performCommand(cmd.substring(4));
    }
}
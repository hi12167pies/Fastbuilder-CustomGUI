package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class MessageAction implements Action {
    @Override
    public String getName() {
        return "msg";
    }

    @Override
    public void executeAction(InventoryClickEvent event, List<String> args, String cmd) {
        event.getWhoClicked().sendMessage(cmd.substring(4).replace("&", "§"));
    }
}

package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderAPI.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class OtherAction implements Action {
    @Override
    public String getName() {
        return "other";
    }

    @Override
    public void executeAction(InventoryClickEvent event, List<String> args, String cmd) {
        if (args.get(1).equalsIgnoreCase("practice")) {
            FastbuilderProvider.getApi().getManager().togglePractice((Player) event.getWhoClicked());
        }
    }
}

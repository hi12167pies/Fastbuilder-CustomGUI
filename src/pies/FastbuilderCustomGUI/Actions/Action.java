package pies.FastbuilderCustomGUI.Actions;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public interface Action {
    String getName();
    void executeAction(InventoryClickEvent event, List<String> args, String cmd);
}

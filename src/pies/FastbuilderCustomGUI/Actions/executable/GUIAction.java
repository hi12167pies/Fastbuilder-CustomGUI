package pies.FastbuilderCustomGUI.Actions.executable;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pies.FastbuilderAPI.GUI;
import pies.FastbuilderCustomGUI.Actions.Action;
import pies.FastbuilderCustomGUI.CustomGUI;
import pies.FastbuilderCustomGUI.CustomGUIHandler;
import pies.FastbuilderCustomGUI.Main;

import java.util.List;

public class GUIAction implements Action {
    @Override
    public String getName() {
        return "gui";
    }

    @Override
    public void executeAction(InventoryClickEvent event, List<String> args, String cmd) {
        Player player = (Player) event.getWhoClicked();
        if (args.get(1).equalsIgnoreCase("close")) {
            player.closeInventory();
            return;
        }
        GUI gui = Main.instance.registeredGUIS.get(args.get(1));
        if (gui == null) {
            try {
                gui = GUI.GUIS.valueOf(args.get(1)).gui;
            } catch (Exception ignored) {}

            if (gui == null) {
                player.sendMessage("GUI Not found: " + args.get(1));
                return;
            }
        }
        CustomGUIHandler.open(player, gui);
    }
}

package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import cf.pies.fastbuilder.api.gui.GUI;
import pies.FastbuilderCustomGUI.Actions.Action;
import pies.FastbuilderCustomGUI.CustomGUIHandler;
import pies.FastbuilderCustomGUI.Main;

import java.util.List;

public class GUIAction implements Action {
    @Override
    public String getName() {
        return "gui";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        if (args.get(1).equalsIgnoreCase("close")) {
            player.getPlayer().closeInventory();
            return;
        }

        GUI gui = Main.instance.registeredGUIS.get(args.get(1));
        if (gui == null) {
            try {
                gui = GUI.GUIS.valueOf(args.get(1)).gui;
            } catch (Exception ignored) {}

            if (gui == null) {
                player.getPlayer().sendMessage("GUI Not found: " + args.get(1));
                return;
            }
        }
        CustomGUIHandler.open(player.getPlayer(), gui);
    }
}

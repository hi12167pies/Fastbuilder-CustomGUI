package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class CommandAction implements Action {
    @Override
    public String getName() {
        return "cmd";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        player.getPlayer().performCommand(cmd.substring(4));
    }
}
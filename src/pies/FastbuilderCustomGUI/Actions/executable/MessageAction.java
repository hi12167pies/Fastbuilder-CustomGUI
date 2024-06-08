package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class MessageAction implements Action {
    @Override
    public String getName() {
        return "msg";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        player.getPlayer().sendMessage(cmd.substring(4).replace("&", "§"));
    }
}

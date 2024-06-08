package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class OtherAction implements Action {
    @Override
    public String getName() {
        return "other";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        if (args.get(1).equalsIgnoreCase("practice")) {
            player.togglePractice();
        }
    }
}

package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class StatsAction implements Action {
    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        if (args.get(1).equals("resetpb")) {
            player.setPersonalBest(player.getMode(), 0);
        } else {
           player.getPlayer().sendMessage("action not found: stats." + args.get(1));
        }
    }
}

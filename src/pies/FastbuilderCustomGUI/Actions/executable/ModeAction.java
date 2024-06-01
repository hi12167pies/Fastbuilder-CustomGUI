package pies.FastbuilderCustomGUI.Actions.executable;

import cf.pies.fastbuilder.api.FastbuilderAPI;
import cf.pies.fastbuilder.api.FastbuilderMode;
import cf.pies.fastbuilder.api.FastbuilderPlayer;
import cf.pies.fastbuilder.api.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.Action;

import java.util.List;

public class ModeAction implements Action {
    FastbuilderAPI api = FastbuilderProvider.getApi();

    @Override
    public String getName() {
        return "mode";
    }

    @Override
    public void executeAction(FastbuilderPlayer player, List<String> args, String cmd) {
        String sMode = args.get(1);
        FastbuilderMode mode = FastbuilderProvider.getApi().getMode(sMode);

        if (mode.nextValidArena() == null) {
            player.getPlayer().sendMessage(api.getMessage("mode_full"));
                return;
            }
        if (player.isWatchingReplay()) {
            player.getPlayer().sendMessage(api.getMessage("cannot_change_mode_in_replay"));
            return;
        }
        if (mode.equals(player.getMode())) {
            player.getPlayer().sendMessage(api.getMessage("already_in_mode"));
            return;
        }
        player.leave(true, true);
        player.join(mode);
    }
}

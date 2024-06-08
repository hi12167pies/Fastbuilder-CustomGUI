package pies.FastbuilderCustomGUI.Actions;

import cf.pies.fastbuilder.api.FastbuilderPlayer;

import java.util.List;

public interface Action {
    String getName();
    void executeAction(FastbuilderPlayer player, List<String> args, String cmd);
}

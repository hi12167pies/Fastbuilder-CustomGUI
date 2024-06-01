package pies.FastbuilderCustomGUI.Actions;

import cf.pies.fastbuilder.api.FastbuilderPlayer;
import cf.pies.fastbuilder.api.FastbuilderProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ActionManager {
    public HashMap<String, Action> actions = new HashMap<>();

    public boolean registerAction(String name, Action action) {
        if (actions.containsKey(name))
            return false;
        this.actions.put(name, action);
        return true;
    }

    public boolean registerAction(Action action) {
        return this.registerAction(action.getName(), action);
    }


    public void run(String action, InventoryClickEvent event) {
        List<String> args = Arrays.asList(action.split("\\."));
        Action act = this.actions.get(args.get(0));
        if (act == null) return;
        Player player = (Player) event.getWhoClicked();
        act.executeAction(FastbuilderProvider.getApi().getPlayer(player), args, action);
    }
}

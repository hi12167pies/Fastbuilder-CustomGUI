package pies.FastbuilderCustomGUI.Actions;

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
        Action a = this.actions.get(args.get(0));
        if (a == null) return;
        a.executeAction(event, args, action);
    }
}

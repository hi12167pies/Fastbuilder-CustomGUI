package pies.FastbuilderCustomGUI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pies.FastbuilderAPI.FastbuilderProvider;
import pies.FastbuilderCustomGUI.Actions.ActionManager;
import pies.FastbuilderCustomGUI.Actions.executable.*;
import pies.FastbuilderCustomGUI.Util.Reflections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main extends JavaPlugin {
    public static Main instance;
    public static ActionManager actionManager = new ActionManager();
    public HashMap<String, CustomGUI> registeredGUIS = new HashMap<>();
    public Set<CustomGUI> handleGUIS = new HashSet<>();
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        register();

        actionManager.registerAction(new MessageAction());
        actionManager.registerAction(new GUIAction());
        actionManager.registerAction(new ModeAction());
        actionManager.registerAction(new CommandAction());
        actionManager.registerAction(new BlockAction());
        actionManager.registerAction(new PickaxeAction());
        actionManager.registerAction(new StatsAction());
        actionManager.registerAction(new OtherAction());

        Bukkit.getPluginManager().registerEvents(new CustomGUIHandler(), this);
        Bukkit.getPluginCommand("reloadguis").setExecutor(new ReloadGUI());
    }

    public void register() {
        this.registeredGUIS.clear();
        this.handleGUIS.clear();

        // register
        for (String name : this.getConfig().getConfigurationSection("gui").getKeys(false)) {
            this.registeredGUIS.put(name, new CustomGUI(name, false, new BlankHolder()));
        }

        // overwrite
        for (String item : this.getConfig().getConfigurationSection("overwrite").getKeys(false)) {
            String value = this.getConfig().getString("overwrite." + item);

            if (value.equals("[none]")) continue;

            Enum<?> gui = FastbuilderProvider.getApi().getETC().a(item);
            if (gui == null) continue;

            CustomGUI registeredGUI = this.registeredGUIS.get(value);
            if (registeredGUI == null) continue;

            registeredGUI.isBound = true;
            Reflections.setField(gui, "gui", registeredGUI);
        }

        // finalize
        for (String s : this.registeredGUIS.keySet()) {
            CustomGUI gui = this.registeredGUIS.get(s);

            if (gui.isBound) continue;

            this.handleGUIS.add(gui);
        }
    }

    public static boolean isPlaceholderAPIInstalled() {
        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        return placeholderAPI != null && placeholderAPI.isEnabled();
    }
}

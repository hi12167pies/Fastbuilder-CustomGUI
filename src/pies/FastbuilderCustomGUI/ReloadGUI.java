package pies.FastbuilderCustomGUI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadGUI implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Main.instance.reloadConfig();
        sender.sendMessage("config reloaded");
        Main.instance.register();
        sender.sendMessage("re-registered guis");
        return true;
    }
}

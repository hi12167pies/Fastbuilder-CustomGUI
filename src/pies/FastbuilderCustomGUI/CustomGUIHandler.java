package pies.FastbuilderCustomGUI;

import cf.pies.fastbuilder.api.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomGUIHandler implements Listener {

    @EventHandler
    void a(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (e.getCurrentItem() == null) return;
        for (CustomGUI gui : Main.instance.handleGUIS) {
            if (gui.getHolder() == e.getInventory().getHolder()) {
                e.setCancelled(true);
                if (gui.getHolder() == e.getClickedInventory().getHolder()) {
                    Player player = (Player) e.getWhoClicked();
                    gui.clickEvent(player, e);
                }
                break;
            }
        }
    }

    public static void open(Player player, GUI gui) {
        player.openInventory(gui.getNewInventory(player));
    }
}

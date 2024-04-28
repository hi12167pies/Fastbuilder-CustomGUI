package pies.FastbuilderCustomGUI;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BlankHolder implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return null;
    }
}

package pies.FastbuilderCustomGUI.Util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    ItemStack itemStack;

    ItemMeta meta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public ItemBuilder durability(int s) {
        this.itemStack.setDurability((short)s);
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder name(String displayName) {
        this.meta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder unBreak() {
        this.meta.spigot().setUnbreakable(true);
        return this;
    }
    public ItemBuilder lore(List<String> lore) {
        this.meta.setLore(lore);
        return this;
    }


    public ItemBuilder lore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder addLore(String... lore) {
        List<String> s = this.meta.getLore();
        if (s == null) {
            this.meta.setLore(Arrays.asList(lore));
        } else {
            s.addAll(Arrays.asList(lore));
            this.meta.setLore(s);
        }
        return this;
    }

    public ItemBuilder hideUnBreak() {
        this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
        return this;
    }

    public ItemBuilder hideAttr() {
        this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        return this;
    }

    public ItemBuilder hideEnch() {
        this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        return this;
    }

    public ItemBuilder hidePotion() {
        this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
        return this;
    }

    @Deprecated
    public ItemBuilder ench(Enchantment[] enchantments, int[] levels) {
        for (int i = 0; i < enchantments.length; i++)
            this.meta.addEnchant(enchantments[i], levels[i], true);
        return this;
    }

    public ItemBuilder ench(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder owner(String name) {
        SkullMeta skull = (SkullMeta)this.meta;
        skull.setOwner(name);
        this.itemStack.setItemMeta((ItemMeta)skull);
        return this;
    }

    public ItemBuilder addPotion(PotionEffect effect) {
        PotionMeta potion = (PotionMeta)this.meta;
        potion.addCustomEffect(effect, true);
        this.itemStack.setItemMeta((ItemMeta)potion);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }
}

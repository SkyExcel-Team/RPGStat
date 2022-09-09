package git.skyexcel.me.data.gui.item;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class UtilItem {
    public static void newItem(String name, Material material, int amount, List<String> lore, int slot, Inventory inv){
        ItemStack item = new ItemStack(material,amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(slot,item);

    }

}

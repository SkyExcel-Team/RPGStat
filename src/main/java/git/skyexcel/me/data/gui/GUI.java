package git.skyexcel.me.data.gui;

import git.skyexcel.me.RPGStatSystem;
import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.item.UtilItem;
import git.skyexcel.me.runnable.LimitGUI;
import git.skyexcel.me.runnable.StatGUI;
import git.skyexcel.me.runnable.UpgradeGUI;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class GUI {

    public static Inventory listGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "스텟 설정");

        UtilItem.newItem(Data.upgradeStat, Material.EMERALD, 1, Arrays.asList(""), 10, inv);
        UtilItem.newItem(Data.limitStat, Material.RECORD_12, 1, Arrays.asList(""), 13, inv);
        UtilItem.newItem(Data.editGUI, Material.NAME_TAG, 1, Arrays.asList(""), 16, inv);
        player.openInventory(inv);
        Data.isAddStast.put(player.getUniqueId(), inv);
        return inv;
    }


    public static void statGUI(Player player) {

        Inventory inv = Bukkit.createInventory(null, 45, player.getDisplayName() + ChatColor.GOLD + " 님의 스탯");

        StatGUI gui = new StatGUI(inv, player);

        gui.runTaskTimer(RPGStatSystem.plugin, 0, 0);

        Data.statTask.put(player.getUniqueId(), gui);

        player.openInventory(inv);
    }


    public static void editGUI(StatConfigData config, StatData data, Player player) {

        Inventory inv = Bukkit.createInventory(null, 27, Data.editGUI);

        String keys = ""; // GUI 열때 키를 가져옴.

        if (keys != null) {
            String name = config.translate(keys);
            int slot = config.getSlots(keys);
            ItemStack item = config.getItems(keys);

            if (item != null) {

                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName(name);
                meta.setLore(Arrays.asList("" + data.addModifier(StatType.valueOf(keys)).getStat()));
                item.setItemMeta(meta);
                inv.setItem(slot, item);

            }
        }

        UtilItem.newItem(ChatColor.GREEN + "[ 남은 스탯 ]", Material.STICK, 1,
                Arrays.asList(ChatColor.GOLD + "남은 스탯: " + ChatColor.RED + data.getStatPoint()), 18, inv);


        player.openInventory(inv);
    }

    public static Inventory upgradeGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, Data.upgradeStat);


        UpgradeGUI gui = new UpgradeGUI(inv, player);

        gui.runTaskTimer(RPGStatSystem.plugin, 0, 10);

        Data.statTask.put(player.getUniqueId(), gui);

        UtilItem.newColoredItem(ChatColor.GRAY + "+ 0.01", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 18, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+ 0.1", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 19, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+ 1", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 20, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+ 10", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 21, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 0.01", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 23, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 0.1", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 24, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 1", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 25, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 10", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 26, inv);
        player.openInventory(inv);

        return inv;
    }


    public static void limitGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, Data.limitStat);

        UtilItem.newColoredItem(ChatColor.GRAY + "100", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 19, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+10", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 20, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "1", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 21, inv);

        LimitGUI gui = new LimitGUI(inv, player);

        gui.runTaskTimer(RPGStatSystem.plugin, 0, 10);

        Data.statTask.put(player.getUniqueId(), gui);

        UtilItem.newColoredItem(ChatColor.GRAY + "- 1", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 23, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 10", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 24, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 100", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 25, inv);
        player.openInventory(inv);
    }
}

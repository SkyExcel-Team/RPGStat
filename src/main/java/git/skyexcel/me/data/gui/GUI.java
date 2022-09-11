package git.skyexcel.me.data.gui;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.item.UtilItem;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.main.RPGStatSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Arrays;

public class GUI {

    public static void listGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "스텟 설정");

        UtilItem.newItem(Data.addStat, Material.EYE_OF_ENDER, 1, Arrays.asList(""), 10, inv);
        UtilItem.newItem(Data.upgradeStat, Material.EMERALD, 1, Arrays.asList(""), 12, inv);
        UtilItem.newItem(Data.limitStat, Material.CHORUS_PLANT, 1, Arrays.asList(""), 14, inv);
        UtilItem.newItem(Data.editGUI, Material.NAME_TAG, 1, Arrays.asList(""), 16, inv);
        player.openInventory(inv);
        Data.isAddStast.put(player.getUniqueId(), inv);
    }


    public static void statGUI(StatConfigData config, StatData data, Player player) {
        ConfigurationSection section = config.getConfig().getConfig().getConfigurationSection("stat");
        Inventory inv = Bukkit.createInventory(null, 27, player.getDisplayName() + ChatColor.GOLD + " 님의 스탯");

        for (String keys : section.getKeys(false)) {
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
        }

        UtilItem.newItem(ChatColor.GREEN + "[ 남은 스탯 ]", Material.STICK, 1,
                Arrays.asList(ChatColor.GOLD + "남은 스탯: " + ChatColor.RED + data.getStatPoint()), 18, inv);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.updateInventory();

            }
        }.runTaskLater(RPGStatSystem.plugin, 1);

        player.openInventory(inv);

    }

    public static void UpgradeGUI(Player player) {
        StatConfigData config = new StatConfigData();
        Inventory inv = Bukkit.createInventory(null, 45, Data.upgradeStat);

        UtilItem.newColoredItem(ChatColor.GRAY + "+ 0.01", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 18, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+ 0.1", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 19, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+ 1", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 20, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "+ 10", Material.STAINED_GLASS_PANE, 1, 11, Arrays.asList(""), 21, inv);

        UtilItem.newItem(ChatColor.GREEN + "[ 강화 여부 ]", Material.EMERALD, 1, Arrays.asList(""), 22, inv);

        UtilItem.newColoredItem(ChatColor.GRAY + "- 0.01", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 23, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 0.1", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 24, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 1", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 25, inv);
        UtilItem.newColoredItem(ChatColor.GRAY + "- 10", Material.STAINED_GLASS_PANE, 1, 14, Arrays.asList(""), 26, inv);
        player.openInventory(inv);
    }
}

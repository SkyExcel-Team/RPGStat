package git.skyexcel.me.runnable;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.item.UtilItem;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class StatGUI extends BukkitRunnable {

    private Inventory inv;

    private Player player;

    private StatConfigData config;

    public StatGUI(Inventory inv, Player player) {
        this.inv = inv;
        this.player = player;
        config = new StatConfigData();
        config.setPlayer(player);
    }

    @Override
    public void run() {
        StatData data = new StatData(player);
        ConfigurationSection section = data.getConfig().getConfig().getConfigurationSection("stat");
        for (String keys : section.getKeys(false)) {
            if (keys != null && !keys.equalsIgnoreCase("points")) {
                String name = config.translate(keys);
                int slot = config.getSlots(keys);

                ItemStack item = config.getItems(keys);

                int limit = (int) data.addModifier(StatType.valueOf(keys)).getLimit();

                if (limit >= 0) {

                    if (item != null) {


                        List<String> lore = item.getItemMeta().getLore();
                        if (lore != null) {
                            UtilItem.newItem(name, item.getType(), 1, Data.translate(player, lore, keys)
                                    , slot, inv);
                        } else {
                            UtilItem.newItem(name, item.getType(), 1, Arrays.asList("")
                                    , slot, inv);
                        }

                    }

                } else {

                    if (item != null) {
                        List<String> lore = item.getItemMeta().getLore();
                        UtilItem.newItem(name, item.getType(), 1, Data.translate(player, lore, keys), slot, inv);
                    }

                }

            }
        }
        UtilItem.newItem(ChatColor.GREEN + "[ 남은 스탯 ]", Material.STICK, 1,
                Arrays.asList(ChatColor.GOLD + "남은 스탯: " + ChatColor.RED + data.getStatPoint()), 36, inv);
    }
}

package git.skyexcel.me.runnable;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.item.UtilItem;
import git.skyexcel.me.data.stat.StatConfigData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class UpgradeGUI extends BukkitRunnable {

    private Inventory inv;
    private StatConfigData config;

    private Player player;

    public UpgradeGUI(Inventory inv, Player player) {
        this.inv = inv;
        this.player = player;
        config = new StatConfigData();
    }


    @Override
    public void run() {
        UtilItem.newItem(ChatColor.GREEN + "[ 강화 여부 ]", Material.EMERALD, 1,
                Arrays.asList(String.format("%.2f", config.getUpgradeKey(Data.type.get(player.getUniqueId()).name()))), 22, inv);
    }
}

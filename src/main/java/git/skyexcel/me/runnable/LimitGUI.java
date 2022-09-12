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

public class LimitGUI extends BukkitRunnable {
    private Inventory inv;

    private Player player;
    private StatConfigData config;

    public LimitGUI(Inventory inv, Player player) {
        this.inv = inv;

        this.player = player;
        config = new StatConfigData();
    }


    @Override
    public void run() {

        UtilItem.newItem(ChatColor.GREEN + "[ 한도 ]", Material.RECORD_12, 1,
                Arrays.asList(config.getLimitKey(Data.type.get(player.getUniqueId()).name()) + ""), 22, inv);

    }
}

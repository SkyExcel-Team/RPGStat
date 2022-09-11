package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BlockBreak implements Listener {

    private static ArrayList<Material> ores = new ArrayList<>();

    static {
        ores.add(Material.COAL_ORE);
        ores.add(Material.IRON_ORE);
        ores.add(Material.GOLD_ORE);
        ores.add(Material.LAPIS_ORE);
        ores.add(Material.GLOWING_REDSTONE_ORE);
        ores.add(Material.QUARTZ_ORE);
        ores.add(Material.REDSTONE_ORE);
        ores.add(Material.EMERALD_ORE);
        ores.add(Material.DIAMOND_ORE);

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();

        StatData stat = new StatData(player);

        if (ores.contains(block.getType())) { //광물을 캘 시
            double mine = stat.addModifier(StatType.Mine).getStat();

            player.sendMessage("test " + mine);
        }
    }

    public void dropItem(Player player, Block block) {
        if(ores.contains(block.getType())){
            
        }
        ItemStack item = new ItemStack(Material.COAL, 2);
        player.getWorld().dropItem(block.getLocation(), item);

    }
}

package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Random;

public class BlockBreak implements Listener {

    private static ArrayList<ItemStack> ores = new ArrayList<>();
    private static ArrayList<ItemStack> item = new ArrayList<>();
    private static ArrayList<ItemStack> farm = new ArrayList<>();

    private static ArrayList<ItemStack> farm_item = new ArrayList<>();

    static {
        ores.add(new ItemStack(Material.COAL_ORE));
        ores.add(new ItemStack(Material.IRON_ORE));
        ores.add(new ItemStack(Material.GOLD_ORE));

        ores.add(new ItemStack(Material.LAPIS_ORE));
        ores.add(new ItemStack(Material.REDSTONE_ORE));
        ores.add(new ItemStack(Material.EMERALD_ORE));
        ores.add(new ItemStack(Material.DIAMOND_ORE));

        item.add(new ItemStack(Material.COAL));
        item.add(new ItemStack(Material.IRON_INGOT));
        item.add(new ItemStack(Material.GOLD_INGOT));

        item.add(new ItemStack(Material.INK_SACK, 1, (short) 4));
        item.add(new ItemStack(Material.REDSTONE));
        item.add(new ItemStack(Material.EMERALD));
        item.add(new ItemStack(Material.DIAMOND));

        farm.add(new ItemStack(Material.MELON_BLOCK));
        farm.add(new ItemStack(Material.PUMPKIN));
        farm.add(new ItemStack(Material.CARROT));
        farm.add(new ItemStack(Material.WHEAT));

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location loc = block.getLocation();

        StatData stat = new StatData(player);
        ItemStack item = new ItemStack(block.getType(), 1);

        if (ores.contains(item)) { //광물을 캘 시
            int indexof = ores.indexOf(item);
            ItemStack dropped = this.item.get(indexof);
            StatConfigData config = new StatConfigData();

            double mine = stat.addModifier(StatType.MINE).getStat() * config.addModifier(StatType.MINE).getUpgrade();

            Random select = new Random();
            int random = select.nextInt((100));

            if (mine <= 50) {
                if (random < mine) {
                    dropped.setAmount(2);
                    loc.getWorld().dropItem(loc, dropped);
                    player.sendMessage("2");
                }
            } else {
                if (random < mine) {
                    dropped.setAmount(3);
                    loc.getWorld().dropItem(loc, dropped);
                    player.sendMessage("3");
                }
            }
        } else if(farm.contains(item)){
            switch (git.skyexcel.me.util.Random.RandomByStat(player, StatType.FISH)){
                case 2:
                    player.sendMessage("2개됨 ㅊㅊ");
                    break;
                case 3:
                    player.sendMessage("3개됨 ㅊㅊ");
                    break;
            }
        }

        event.setCancelled(true);
    }

}

package git.skyexcel.me.util;

import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.entity.Player;

public class Random {
    public static int RandomByStat(Player player, StatType type){
        StatConfigData config = new StatConfigData();
        StatData stat = new StatData(player);
        double mine = stat.addModifier(type).getStat() * config.addModifier(type).getUpgrade();

        java.util.Random select = new java.util.Random();
        int random = select.nextInt((100));

        if (mine <= 50) { // 49
            if (random < mine) {
                return 2;
            }
        } else {
            if (random < mine) { // 51
                return 3;
            }
        }
        return -1;
    }

    public static boolean randomOverByStat(Player player,StatType type){
        StatConfigData config = new StatConfigData();
        StatData stat = new StatData(player);
        double mine = stat.addModifier(type).getStat() * config.addModifier(type).getUpgrade();

        java.util.Random select = new java.util.Random();
        int random = select.nextInt((100));
        if (random < mine) {


            return true;
        }
        return false;
    }
}

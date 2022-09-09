package git.skyexcel.me.data;

import data.Config;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Stat {
    private Player player;
    private StatType statType;
    private Config config;

    public Stat(Player player) {
        this.player = player;
    }

    public StatType getStat(){
        return statType;
    }



    public void AddHealth(){
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
    }

    public Player getPlayer() {
        return player;
    }


}

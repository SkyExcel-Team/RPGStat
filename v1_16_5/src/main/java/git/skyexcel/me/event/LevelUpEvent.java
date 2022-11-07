package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.Stat;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class LevelUpEvent implements Listener {

    @EventHandler
    public void onLevelUp(PlayerLevelChangeEvent event){
        Player player = event.getPlayer();
        StatData stat = new StatData(player);
        stat.increaseStatPoint(1);
        player.sendMessage("§b레벨업을 하여 스탯포인트 1을 얻었습니다!");
    }
}

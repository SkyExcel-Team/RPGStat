package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        StatConfigData config = new StatConfigData();

        StatData data = new StatData(player);

        data.setDefaultStatPoint(config.getDefaultPoint());
        config.setPlayer(player);
        config.test(data);
        
    }
}

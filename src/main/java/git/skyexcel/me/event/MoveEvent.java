package git.skyexcel.me.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class MoveEvent implements Listener {


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();




    }

}

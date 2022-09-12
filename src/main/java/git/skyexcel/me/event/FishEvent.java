package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.util.Random;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishEvent implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent event) throws InterruptedException {
        Player player = event.getPlayer();
        Fish fish = event.getHook();

        switch (Random.RandomByStat(player, StatType.FISH)){
            case 2:
                event.wait(10);
                break;
            case 3:

                break;
        }
    }
}

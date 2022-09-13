package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishEvent implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if (event.getCaught() instanceof Fish) {
            switch (event.getState()) {
                case BITE:
                    Material mat = ((Item) event.getCaught()).getItemStack().getType();

                    ItemStack itemStack = new ItemStack(mat);
                    switch (Random.RandomByStat(player, StatType.FISH)) {
                        case 2:
                            itemStack.setAmount(2);
                            player.getWorld().dropItem(player.getLocation(),itemStack);

                            break;
                        case 3:
                            itemStack.setAmount(3);
                            player.getWorld().dropItem(player.getLocation(),itemStack);
                            break;
                    }
                    break;

            }
        }
    }
}

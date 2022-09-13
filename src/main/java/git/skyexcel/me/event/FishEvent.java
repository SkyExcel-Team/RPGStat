package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.util.Random;
import net.minecraft.server.v1_12_R1.EntityFishingHook;
import net.minecraft.server.v1_12_R1.ItemStack;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import java.lang.reflect.Field;

public class FishEvent implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent event){
        Player player = event.getPlayer();
        FishHook hook = event.getHook();
        StatData data = new StatData(player);
        StatConfigData config = new StatConfigData();

        int stat = (int) data.addModifier(StatType.FISH).getStat();
        double upgrade = config.addModifier(StatType.FISH).getUpgrade();

        Fish fish = (Fish) event.getCaught();
        fish.getType().getName();


        switch (event.getState()) {
            case FISHING:
                setBiteTime(hook, getBiteTime(hook) + stat);
                switch (Random.RandomByStat(player,StatType.FISH)){
                    case 2:


                        break;
                    case 3:
                        player.sendMessage("물고기 3개 ");
                        break;
                }
                break;
        }
    }

    private void setBiteTime(FishHook hook, int time) {
        EntityFishingHook hookCopy = (EntityFishingHook) ((CraftEntity) hook).getHandle();

        Field fishCatchTime = null;

        try {
            fishCatchTime = EntityFishingHook.class.getDeclaredField("h");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        fishCatchTime.setAccessible(true);
        try {
            fishCatchTime.setInt(hookCopy, time);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        fishCatchTime.setAccessible(false);
    }

    private int getBiteTime(FishHook hook) {
        EntityFishingHook hookCopy = (EntityFishingHook) ((CraftEntity) hook).getHandle();

        Field fishCatchTime = null;

        try {
            fishCatchTime = EntityFishingHook.class.getDeclaredField("h");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        fishCatchTime.setAccessible(true);

        try {
            return fishCatchTime.getInt(hookCopy);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        fishCatchTime.setAccessible(false);
        return -1;
    }
}

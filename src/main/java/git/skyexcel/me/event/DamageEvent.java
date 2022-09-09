package git.skyexcel.me.event;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.Potion;

public class DamageEvent implements Listener {
    @EventHandler
    public void getDamage(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        double damage = event.getDamage();
        EntityDamageEvent.DamageCause cause = event.getCause();
        switch (cause){
            case FALL :

                break;
            case ENTITY_ATTACK:

                if(event.getDamager() instanceof ThrownPotion){//Potion 데미지를 입을때
                    ThrownPotion potion = (ThrownPotion) event.getDamager();

                } else if(event.getDamager() instanceof Fireball){//FireBall로 데미지를 입을때
                    ThrownPotion potion = (ThrownPotion) event.getDamager();
                } else if(event.getDamager() instanceof Arrow){ //화살로 데미지를 입을때
                    Arrow arrow = (Arrow) event.getDamager();

                }
                break;
        }
    }
}

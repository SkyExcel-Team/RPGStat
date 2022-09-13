package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.util.Random;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class DamageEvent implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        double damage = event.getDamage();
        EntityDamageEvent.DamageCause cause = event.getCause();


        switch (cause) { //낙하 데미지
            case FALL:
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    StatData data = new StatData(player);
                    double fall = data.addModifier(StatType.FALL).getStat();
                    StatConfigData config = new StatConfigData();

                    double result = damage * (1 / ((1 + (fall) * config.addModifier(StatType.FALL).getUpgrade()))); // 강화율과 방어력을 곱해 방어률에 적용시킨다.

                    event.setDamage(result);
                }
                break;
        }
    }

    @EventHandler
    public void getDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        double damage = event.getDamage();

        EntityDamageEvent.DamageCause cause = event.getCause();
        switch (cause) {
            case ENTITY_ATTACK:

                if (entity instanceof Player) {

                    if (damager instanceof Player) { // 데미지를 입힌 사람이 플레일 경우,

                        Player player = (Player) damager; //데미저를 플레이어로 변환한다
                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.

                        StatData target_data = new StatData(target);

                        StatConfigData config = new StatConfigData();
                        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

                        double defense = target_data.addModifier(StatType.DEFENSE).getStat();

                        double newdamage = damage * (1 / (1 + (defense * upgrade)));
                        if(Random.randomOverByStat(player,StatType.CRITICAL_DAMAGE)){
                            event.setDamage(newdamage * 2);
                        } else{
                            event.setDamage(newdamage);
                        }
                        target.sendMessage("방어력 : " + defense + " 데미지 : " + damage + " 방어한 데미지 " + newdamage);

                    } else if (!(damager instanceof Player)) { // 데미지를 입은 사람이 플레이어고, 플레이어를 때린 대상이 몹일 경우

                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.
                        StatData target_data = new StatData(target);
                        StatConfigData config = new StatConfigData();

                        double newdamage = damage(config, target_data, damage);
                        event.setDamage(newdamage);
                    }
                } else { // 때린 타겟이 플레이어가 아닐 경우
                    // 원거리공격력
                    if (damager instanceof Arrow) { // 몬스터가ㄴ 화살에 맞았을때
                        Arrow arrow = (Arrow) event.getDamager();
                        if (arrow.getShooter() instanceof Player) { // 화살을 쏜 사람이 플레이어 일 경우
                            Player shooter = (Player) arrow.getShooter();
                            StatConfigData config = new StatConfigData();
                            double upgrade = config.addModifier(StatType.RANGED_ATTACK_DAMAGE).getUpgrade();

                            StatData shooter_data = new StatData(shooter);
                            double shooter_damage = shooter_data.addModifier(StatType.RANGED_ATTACK_DAMAGE).getStat();

                            double newdamage = shooter_damage * (1 / (1 + (getEPF(((PlayerInventory) entity)) * upgrade))); //방어률과 비례하여 데미지를 설정

                            if(Random.randomOverByStat(shooter,StatType.CRITICAL_DAMAGE)){
                                event.setDamage(newdamage * 2);
                            } else{
                                event.setDamage(newdamage);
                            }
                        }
                    } else {// 플레이어가 근접 공격에 맞았을때
                        Player player = (Player) damager;
                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.
                        StatData target_data = new StatData(target);
                        StatConfigData config = new StatConfigData();

                        double newdamage = damage(config, target_data, damage);
                        if(Random.randomOverByStat(player,StatType.CRITICAL_DAMAGE)){
                            event.setDamage(newdamage * 2);
                        } else{
                            event.setDamage(newdamage);
                        }

                    }
                }
                break;
        }
    }
    public static int getEPF(PlayerInventory inv) {
        ItemStack helm = inv.getHelmet();
        ItemStack chest = inv.getChestplate();
        ItemStack legs = inv.getLeggings();
        ItemStack boot = inv.getBoots();

        return (helm != null ? helm.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (chest != null ? chest.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (legs != null ? legs.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (boot != null ? boot.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0);
    }

    public double damage(StatConfigData config, StatData data, double damage) {
        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

        double defense = data.addModifier(StatType.DEFENSE).getStat();

        return damage * (1 / ((1 + (defense) * upgrade))); // 강화율과 방어력을 곱해 방어률에 적용시킨다.
    }
}

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

import java.util.ArrayList;


public class DamageEvent implements Listener {


    private static ArrayList<EntityType> entities = new ArrayList<>();

    static {
        entities.add(EntityType.SKELETON);
        entities.add(EntityType.WITHER_SKELETON);
        entities.add(EntityType.ZOMBIE);
        entities.add(EntityType.PLAYER);
    }

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

                    // 강화율과 방어력을 곱해 방어률에 적용시킨다. FAll 값이 0 일경우 일반 데미지를 입힘.
                    double result = (fall != 0 ? damage * (1 / ((1 + (fall) * config.addModifier(StatType.FALL).getUpgrade()))) : event.getDamage());


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
            case PROJECTILE:

                Arrow arrow = (Arrow) event.getDamager();

                if (arrow.getShooter() instanceof Player) { //화살을 쏜 사람이 플레이어일 경우
                    Player player = (Player) arrow.getShooter();
                    if (entity instanceof Player) {
                        Player target = (Player) entity;
                        StatData target_data = new StatData(target);
                        StatData player_data = new StatData(player);

                        StatConfigData config = new StatConfigData();

                        double ranged_damage = player_data.addModifier(StatType.RANGED_ATTACK_DAMAGE).getStat();

                        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

                        double defense = target_data.addModifier(StatType.DEFENSE).getStat();

                        double newdamage = (defense != 0 ? damage * (1 / (1 + (((defense * upgrade + ranged_damage))))) : damage + ranged_damage);


                        event.setDamage(newdamage);


                    } else {
                        StatData player_data = new StatData(player);

                        double ranged_damage = player_data.addModifier(StatType.RANGED_ATTACK_DAMAGE).getStat();

                        apply_damage(event, entity, ranged_damage);
                    }
                }
                break;
            case ENTITY_ATTACK:
                if (entity instanceof Player) {
                    if (damager instanceof Player) { // 데미지를 입힌 사람이 플레일 경우,

                        Player player = (Player) damager; //데미저를 플레이어로 변환한다
                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.

                        StatData target_data = new StatData(target);


                        StatConfigData config = new StatConfigData();

                        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

                        double defense = (target_data.addModifier(StatType.DEFENSE).getStat());

                        StatData player_data = new StatData(player);

                        double statdamage = player_data.addModifier(StatType.ATTACK_DAMAGE).getStat();

                        double newdamage = (defense != 0 ? damage * (1 / (1 + (((defense * upgrade))))) : damage + statdamage);


                        if (Random.randomOverByStat(player, StatType.CRITICAL_DAMAGE)) {
                            event.setDamage(newdamage * 2);
                        } else {
                            event.setDamage(newdamage);
                        }


                    } else if (!(damager instanceof Player)) { // 데미지를 입은 사람이 플레이어고, 플레이어를 때린 대상이 몹일 경우
                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.
                        StatData target_data = new StatData(target);
                        StatConfigData config = new StatConfigData();

                        double newdamage = damage(config, target_data, damage, target.getInventory());
                        event.setDamage(newdamage);
                    }
                } else {
                    apply_damage(event, entity, 500);
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

    public double damage(StatConfigData config, StatData data, double damage, PlayerInventory inv) {
        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

        double defense = data.addModifier(StatType.DEFENSE).getStat();

        return (damage * (1 / ((1 + (defense + getEPF(inv) * upgrade))))); // 강화율과 방어력을 곱해 방어률에 적용시킨다.
    }

    public void apply_damage(EntityDamageByEntityEvent event, Entity entity, double damage) {
        if (entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;

            ItemStack helm = zombie.getEquipment().getHelmet();
            ItemStack chest = zombie.getEquipment().getChestplate();
            ItemStack legs = zombie.getEquipment().getLeggings();
            ItemStack boot = zombie.getEquipment().getBoots();

            double defense = (helm != null ? helm.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (chest != null ? chest.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (legs != null ? legs.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (boot != null ? boot.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0);

            double newdamage = damage * (1 / (1 + defense));

            event.setDamage(newdamage);
        } else if (entity instanceof Skeleton) {
            Skeleton zombie = (Skeleton) entity;

            ItemStack helm = zombie.getEquipment().getHelmet();
            ItemStack chest = zombie.getEquipment().getChestplate();
            ItemStack legs = zombie.getEquipment().getLeggings();
            ItemStack boot = zombie.getEquipment().getBoots();

            double defense = (helm != null ? helm.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (chest != null ? chest.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (legs != null ? legs.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (boot != null ? boot.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0);

            double newdamage = damage * (1 / (1 + defense));

            event.setDamage(newdamage);
        } else if (entity instanceof WitherSkeleton) {
            WitherSkeleton zombie = (WitherSkeleton) entity;

            ItemStack helm = zombie.getEquipment().getHelmet();
            ItemStack chest = zombie.getEquipment().getChestplate();
            ItemStack legs = zombie.getEquipment().getLeggings();
            ItemStack boot = zombie.getEquipment().getBoots();

            double defense = (helm != null ? helm.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (chest != null ? chest.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (legs != null ? legs.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                    (boot != null ? boot.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0);

            double newdamage = damage * (1 / (1 + defense));

            event.setDamage(newdamage);

        } else if (!entities.contains(entity)) {

            double newdamage = damage + event.getDamage();

            event.setDamage(newdamage);
        }
    }
}

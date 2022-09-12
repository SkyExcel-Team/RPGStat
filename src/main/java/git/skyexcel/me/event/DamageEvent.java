package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageEvent implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        double damage = event.getDamage();
        EntityDamageEvent.DamageCause cause = event.getCause();


        switch (cause) {
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
        entity.sendMessage(cause.name());
        switch (cause) {
            case ENTITY_ATTACK:
                if (entity instanceof Player) {

                    if (damager instanceof Player) { // 데미지를 입힌 사람이 플레일 경우,
                        Player player = (Player) damager; //데미저를 플레이어로 변환한다
                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.

                        StatData player_data = new StatData(player);
                        StatData target_data = new StatData(target);

                        double attack_damage = player_data.addModifier(StatType.ATTACK_DAMAGE).getStat();
                        double ciritical_chance = player_data.addModifier(StatType.CRITICAL_DAMAGE).getStat();

                        StatConfigData config = new StatConfigData();
                        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

                        double defense = target_data.addModifier(StatType.DEFENSE).getStat();

                        double newdamage = damage * (1 / (1 + (defense * upgrade)));
                        event.setDamage(newdamage);

                        target.sendMessage("방어력 : " + defense + " 데미지 : " + damage + " 방어한 데미지 " + newdamage);
                        damager.sendMessage("test");


                    } else { // 데미지를 입은 사람이 플레이어고, 플레이어를 때린 대상이 몹일 경우

                        Player target = (Player) entity; //데미지를 입은 사람을 타겟으로 지정한다.
                        StatData target_data = new StatData(target);
                        StatConfigData config = new StatConfigData();

                        double newdamage = damage(config, target_data, damage);

                        event.setDamage(newdamage);
                    }
                } else {
                    Player player = (Player) damager;
                    StatData data = new StatData(player);

                    double attackDamage = data.addModifier(StatType.ATTACK_DAMAGE).getStat();

                    event.setDamage(attackDamage + event.getDamage());
                    double critical = attackDamage *= 2F;


                    player.sendMessage(ChatColor.GOLD + "Critical!" + critical);
                }

                if (event.getDamager() instanceof ThrownPotion) {//Potion 데미지를 입을때
                    ThrownPotion potion = (ThrownPotion) event.getDamager();

                    if (entity instanceof Player) { // 데미지를 입는 엔티티가 플레이어 일 경우

                        Player target = (Player) damager;
                        Player player = (Player) event.getEntity();
                        StatData data = new StatData(player);

                        double player_damage = data.addModifier(StatType.ATTACK_DAMAGE).getStat();
                        double armorPoints = target.getAttribute(Attribute.GENERIC_ARMOR).getValue();
                        double armorToughness = target.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();

                        PotionEffect effect = target.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        int resistance = effect == null ? 0 : effect.getAmplifier();
                        int epf = getEPF(target.getInventory());

                        target.damage(calculateDamageApplied(damage, armorPoints, armorToughness, resistance, epf));
                    } else {
                        Player player = (Player) damager;
                        player.sendMessage("test");
                    }
                } else if (event.getDamager() instanceof Fireball) {//FireBall로 데미지를 입을때
                    ThrownPotion potion = (ThrownPotion) event.getDamager();

                } else if (event.getDamager() instanceof Arrow) { //화살로 데미지를 입을때
                    Arrow arrow = (Arrow) event.getDamager();
                    if (arrow.getShooter() instanceof Player) { //플레이어가 화살로 공격을 했을 때
                        Player player = (Player) arrow.getShooter();
                        StatData data = new StatData(player);
                        double player_range_damage = data.addModifier(StatType.RANGED_ATTACK_DAMAGE).getStat();
                    }
                }
                break;
        }
    }

    public double damage(StatConfigData config, StatData data, double damage) {
        double upgrade = config.addModifier(StatType.DEFENSE).getUpgrade();

        double defense = data.addModifier(StatType.DEFENSE).getStat();

        return damage * (1 / ((1 + (defense) * upgrade))); // 강화율과 방어력을 곱해 방어률에 적용시킨다.
    }

    public double calculateDamageApplied(double damage, double points, double toughness, int resistance, int epf) {
        double withArmorReduction = damage * (1 - Math.min(20, Math.max(points / 5, points - damage / (2 + toughness / 4))) / 25);
        double withResistanceReduction = withArmorReduction * (1 - (resistance * 0.2));
        return withResistanceReduction * (1 - (Math.min(20.0, epf) / 25));

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
}

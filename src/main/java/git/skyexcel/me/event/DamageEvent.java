package git.skyexcel.me.event;

import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageEvent implements Listener {
    @EventHandler
    public void getDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        double damage = event.getDamage();

        EntityDamageEvent.DamageCause cause = event.getCause();
        switch (cause) {

            case FALL:

                break;
            case ENTITY_ATTACK:

                if (entity instanceof Player) {

                    Player player = (Player) entity;
                    player.sendMessage("데미지 받음");
                } else {
                    Player player = (Player) damager;
                    StatData data = new StatData(player);
                    double attackDamage = data.addModifier(StatType.Attack_Damage).getStat();
                    event.setDamage(attackDamage + event.getDamage());
                    double newdamage = damage *= 1.5F;
                    player.sendMessage(newdamage + "" );


                }
                if (event.getDamager() instanceof ThrownPotion) {//Potion 데미지를 입을때
                    ThrownPotion potion = (ThrownPotion) event.getDamager();

                    if (entity instanceof Player) { // 데미지를 입는 엔티티가 플레이어 일 경우

                        Player target = (Player) damager;
                        Player player = (Player) event.getEntity();
                        StatData data = new StatData(player);

                        double player_damage = data.addModifier(StatType.Attack_Damage).getStat();
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
                        double player_range_damage = data.addModifier(StatType.Ranged_Attack_Damage).getStat();
                    }
                }
                break;
        }
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

    public void defense(Player player, double damage) {
        StatData target_data = new StatData(player);
        double defense = target_data.addModifier(StatType.Defense).getStat();

    }
}

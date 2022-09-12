package git.skyexcel.me.data.stat;


import git.skyexcel.me.RPGStatSystem;
import git.skyexcel.me.data.Config;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

public class StatData implements Stat {
    private Player player;
    private StatType statType;
    private Config config;


    public StatData(Player player) {
        this.player = player;
        config = new Config(RPGStatSystem.getDataPath(player));
        this.config.setPlugin(RPGStatSystem.plugin);
    }

    @Override
    public StatData addModifier(StatType stat) {
        this.statType = stat;
        return this;
    }

    @Override
    public void setStat(double value) {
        Objects.requireNonNull(statType, "StatType is null!");
        if (decreasePoint(1)) {
            switch (statType) {
                case MAX_HEALTH:
                    setValue("stat." + statType.name(), value);
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getMaxHealth() + value);
                    player.sendMessage("성공적으로 체력 스텟을 쌓았습니다! 남은 스텟 포인트 : " + getStatPoint());
                    break;
                case FALL:
                    setValue("stat." + statType.name(), value);
                    break;
                case FARM:
                    setValue("stat." + statType.name(), value);
                    break;
                case DEFENSE:
                    setValue("stat." + statType.name(), value);
                    player.sendMessage("성공적으로 방어 스텟을 쌓았습니다! 남은 스텟 포인트 : " + getStatPoint());

                    break;

                case FISH:
                    setValue("stat." + statType.name(), value);
                    break;
                case MINE:
                    setValue("stat." + statType.name(), value);
                    break;
                case SPEED:
                    setValue("stat." + statType.name(), value);
                    break;
                case ATTACK_DAMAGE:
                    setValue("stat." + statType.name(), value);
                    player.sendMessage("성공적으로 공격력 스텟을 쌓았습니다! 남은 스텟 포인트 : " + getStatPoint());
                    break;
                case CRITICAL_DAMAGE:
                    setValue("stat." + statType.name(), value);
                    break;
                case RANGED_ATTACK_DAMAGE:

                    setValue("stat.", value);
                    break;
                case LEVELUP:

                    setValue("stat.", value);
                    break;
            }
        }

        config.saveConfig();
    }

    public void increaseStatPoint(int value) {
        String path = "stat.points";
        if (config.getConfig().get(path) == null)
            config.setInteger(path, 0);
        config.setInteger(path, config.getInteger(path) + value);
    }

    public void setStatPoint(int value) {
        String path = "stat.points";

        config.setInteger(path, value);
    }

    public void setDefaultStatPoint(int value) {
        String path = "stat.points";
        if (config.getConfig().get(path) == null)
            config.setInteger(path, value);
    }

    public boolean decreasePoint(int value) {
        String path = "stat.points";
        if (config.getInteger(path) - value >= 0) {
            config.setInteger(path, config.getInteger(path) - value);
            return true;
        } else {
            player.sendMessage("§c> 스텟 포인트가 부족합니다!");
        }
        return false;
    }

    public Integer getStatPoint() {
        return config.getInteger("stat.points");
    }

    public double getStat() {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(statType, "StatType is null!");

        switch (statType) {
            case MAX_HEALTH:
                return config.getDouble("stat." + statType.name());
            case FALL:
                return config.getDouble("stat." + statType.name());
            case FARM:
                return config.getDouble("stat." + statType.name());
            case MINE:
                return config.getDouble("stat." + statType.name());
            case SPEED:
                return config.getDouble("stat." + statType.name());
            case ATTACK_DAMAGE:
                return config.getDouble("stat." + statType.name());
            case CRITICAL_DAMAGE:
                return config.getDouble("stat." + statType.name());
            case DEFENSE:
                return config.getDouble("stat." + statType.name());
            case FISH:
                return config.getDouble("stat." + statType.name());
            case LEVELUP:
                return config.getDouble("stat." + statType.name());
            case RANGED_ATTACK_DAMAGE:
                return config.getDouble("stat.ranged_damage");
        }
        return -1;
    }

    private void setValue(String path, double value) {
        if (config.getConfig().get(path) == null)
            config.setDouble(path, value);
        config.setDouble(path, config.getDouble(path) + value);
    }

    public Config getConfig() {
        return config;
    }
}

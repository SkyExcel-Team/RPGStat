package git.skyexcel.me.data.stat;

import data.Config;
import git.skyexcel.me.Main;
import org.bukkit.entity.Player;

import java.util.Objects;

public class StatData implements Stat{
    private Player player;
    private StatType statType;
    private Config config;

    public StatData(Player player) {
        this.player = player;
        config = new Config(Main.getDataPath(player));

    }

    @Override
    public StatData addModifier(StatType stat) {
        this.statType = stat;
        return this;
    }

    @Override
    public void setStat(double value) {
        Objects.requireNonNull(statType, "StatType is null!");

        switch (statType){
            case Max_Health:
                setValue("stat.health",value);
                break;
            case Fall:
                setValue("stat.fall",value);
                break;
            case Farm:
                setValue("stat.farm",value);
                break;
            case Mine:
                setValue("stat.mine",value);
                break;
            case Speed:
                setValue("stat.speed",value);
                break;
            case Attack_Damage:
                setValue("stat.attack_damage",value);
                break;
            case Critical_Damage:
                setValue("stat.critical_damage",value);
                break;
            case Ranged_Attack_Damage:

                setValue("stat.ranged_damage",value);
                break;
            case LevelUp:

                setValue("stat.stat",value);
                break;
        }
        config.saveConfig();
    }

    public double getStat() {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(statType, "StatType is null!");

        switch (statType) {
            case Max_Health:
                return config.getDouble("stat.health");
            case Fall:
                return config.getDouble("stat.fall");
            case Farm:
                return config.getDouble("stat.farm");
            case Mine:
                return config.getDouble("stat.mine");
            case Speed:
                return config.getDouble("stat.speed");
            case Attack_Damage:
                return config.getDouble("stat.attack_damage");
            case Critical_Damage:
                return config.getDouble("stat.critical_damage");

            case Ranged_Attack_Damage:
                return config.getDouble("stat.ranged_damage");
        }
        return -1;
    }
    private void setValue(String path, double value){
        if(config.getConfig().get(path) == null)
            config.setDouble(path,value);
        config.setDouble(path,config.getDouble(path) + value);
    }
}

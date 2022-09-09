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
                setValue("stat." + statType.name(),value);
                break;
            case Fall:
                setValue("stat." + statType.name(),value);
                break;
            case Farm:
                setValue("stat." + statType.name(),value);
                break;
            case Mine:
                setValue("stat." + statType.name(),value);
                break;
            case Speed:
                setValue("stat." + statType.name(),value);
                break;
            case Attack_Damage:
                setValue("stat." + statType.name(),value);
                break;
            case Critical_Damage:
                setValue("stat." + statType.name(),value);
                break;
            case Ranged_Attack_Damage:

                setValue("stat.",value);
                break;
            case LevelUp:

                setValue("stat.",value);
                break;
        }
        config.saveConfig();
    }

    public double getStat() {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(statType, "StatType is null!");

        switch (statType) {
            case Max_Health:
                return config.getDouble("stat." +statType.name());
            case Fall:
                return config.getDouble("stat." + statType.name());
            case Farm:
                return config.getDouble("stat." + statType.name());
            case Mine:
                return config.getDouble("stat." + statType.name());
            case Speed:
                return config.getDouble("stat." + statType.name());
            case Attack_Damage:
                return config.getDouble("stat." + statType.name());
            case Critical_Damage:
                return config.getDouble("stat." + statType.name());

            case Ranged_Attack_Damage:
                return config.getDouble("stat.ranged_damage");
        }
        return -1;
    }
    public void getAllStat(){

    }

    private void setValue(String path, double value){
        if(config.getConfig().get(path) == null)
            config.setDouble(path,value);
        config.setDouble(path,config.getDouble(path) + value);
    }
}

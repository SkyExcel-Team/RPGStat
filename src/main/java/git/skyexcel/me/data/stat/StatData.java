package git.skyexcel.me.data.stat;


import git.skyexcel.me.RPGStatSystem;
import git.skyexcel.me.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class StatData implements Stat{
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

        switch (statType){
            case MAX_HEALTH:
                setValue("stat." + statType.name(),value);
                break;
            case FALL:
                setValue("stat." + statType.name(),value);
                break;
            case FARM:
                setValue("stat." + statType.name(),value);
                break;
            case MINE:
                setValue("stat." + statType.name(),value);
                break;
            case SPEED:
                setValue("stat." + statType.name(),value);
                break;
            case ATTACK_DAMAGE:
                setValue("stat." + statType.name(),value);
                break;
            case CRITICAL_DAMAGE:
                setValue("stat." + statType.name(),value);
                break;
            case RANGED_ATTACK_DAMAGE:

                setValue("stat.",value);
                break;
            case LEVELUP:

                setValue("stat.",value);
                break;
        }
        config.saveConfig();
    }

    public double getStat() {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(statType, "StatType is null!");

        switch (statType) {
            case MAX_HEALTH:
                return config.getDouble("stat." +statType.name());
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

            case RANGED_ATTACK_DAMAGE:
                return config.getDouble("stat.ranged_damage");
        }
        return -1;
    }

    public void statGUI(StatConfigData data){
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");
        Inventory inv = Bukkit.createInventory(null,27,player.getDisplayName() + ChatColor.GOLD + " 님의 스텟");
        for(String keys : section.getKeys(true)){
            double stats = addModifier(StatType.valueOf(keys)).getStat();
            String name = data.translate(keys);
            ItemStack item = data.getItems(keys);

            System.out.print(name + " : " + stats);
        }
        player.openInventory(inv);
    }

    private void setValue(String path, double value){
        if(config.getConfig().get(path) == null)
            config.setDouble(path,value);
        config.setDouble(path,config.getDouble(path) + value);
    }
    public void listStat(Player target) {
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");

        target.sendMessage(player.getDisplayName() + " 님의 스텟");


        target.sendMessage("§a스텟 포인트: §7" + getStatPoint());
        for (String key : section.getKeys(true)) {
            if (!key.equalsIgnoreCase("points")) {
                double stats = addModifier(StatType.valueOf(key)).getStat();
                StatConfigData config = new StatConfigData();

                target.sendMessage(config.translate(key) + " §a: §7" + stats);
            }
        }
    }
    public void resetStat() {
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");

        for (String key : section.getKeys(true)) {
            if (!key.equalsIgnoreCase("points")) {
                section.set(key, 0);
            }
        }
        config.saveConfig();
    }
    public void setStatPoint(int value) {
        String path = "stat.points";

        config.setInteger(path, value);
    }

    public void collapseStat() {
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");
        int stat = 0;
        for (String key : section.getKeys(true)) {
            if (!key.equalsIgnoreCase("points")) {
                double stats = addModifier(StatType.valueOf(key)).getStat();
                stat += stats;
                section.set(key, 0);
            }
        }

        section.set("points", stat + section.getDouble("points"));
        config.saveConfig();
    }
    public void addStat(double value) {
        String path = "stat." + statType.name();

        config.setDouble(path, config.getDouble(path) + value);
    }
    public void changeStat(double value) {
        String path = "stat." + statType.name();

        config.setDouble(path, value);
    }

    public void increaseValue(String path, double value) {
        if (config.getConfig().get(path) == null)
            config.setDouble(path, value);
        config.setDouble(path, config.getDouble(path) - value);
    }

    public Integer getStatPoint() {
        return config.getInteger("stat.points");
    }

    public Config getConfig() {
        return config;
    }
}

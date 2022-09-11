package git.skyexcel.me.data.stat;

import de.tr7zw.nbtapi.NBTItem;
import git.skyexcel.me.main.RPGStatSystem;
import git.skyexcel.me.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class StatConfigData implements Stat {

    private StatType statType;
    private String name;
    private double value;

    private Config config;

    private Player player;

    public StatConfigData() {
        this.config = RPGStatSystem.config;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public StatConfigData addModifier(StatType stat) {
        if (config.getConfig().get("stat." + stat.name()) != null) {
            this.statType = stat;
            return this;
        }
        return this;
    }

    @Override
    public void setStat(double value) {
        Objects.requireNonNull(statType, "StatType is null!");

        switch (statType) {
            case Max_Health:
                setValue("stat." + statType.name(), value);
                break;
            case Fall:
                setValue("stat." + statType.name(), value);
                break;
            case Farm:
                setValue("stat." + statType.name(), value);
                break;
            case Mine:
                setValue("stat." + statType.name(), value);
                break;
            case Fish:
                setValue("stat." + statType.name(), value);
                break;

            case Defense:
                setValue("stat." + statType.name(), value);
                break;

            case Speed:
                setValue("stat." + statType.name(), value);
                break;
            case Attack_Damage:
                setValue("stat." + statType.name(), value);
                break;
            case Critical_Damage:
                setValue("stat." + statType.name(), value);
                break;
            case Ranged_Attack_Damage:

                setValue("stat.", value);
                break;
            case LevelUp:

                setValue("stat.", value);
                break;
        }
        config.saveConfig();
    }

    private void setValue(String path, double value) {
        if (config.getConfig().get(path) == null)
            config.setDouble(path, value);
        config.setDouble(path, config.getDouble(path) + value);
    }


    public void setItem(String name, int slot, StatType key) {
        this.name = name;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem item = new NBTItem(itemStack);
        item.applyNBT(itemStack);

        config.setString("stat." + key + ".name", key.name());
        config.setInteger("stat." + key + ".slot", slot);

        config.setDouble("stat." + key + ".upgrade", 1);
        config.setDouble("stat." + key + ".limit", 0);
        config.getConfig().set("stat." + key + ".item", player.getInventory().getItemInMainHand());
        config.saveConfig();
    }


    public String translate(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");
        String result = config.getString("stat." + key + ".name");
        switch (key) {
            case "Max_Health":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Fall":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Farm":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Mine":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Speed":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Attack_Damage":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Critical_Damage":
                return ChatColor.translateAlternateColorCodes('&', result);

            case "Ranged_Attack_Damage":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "Defense":
                return ChatColor.translateAlternateColorCodes('&', result);
        }

        return null;
    }

    public ItemStack getItems(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");
        ItemStack item;
        switch (key) {
            case "Max_Health":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");

                return item;
            case "Fall":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "Farm":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "Mine":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "Speed":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "Attack_Damage":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "Critical_Damage":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;

            case "Ranged_Attack_Damage":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "Defense":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;

        }
        statType = null;
        return null;
    }

    public int getSlots(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");

        switch (key) {
            case "Max_Health":

                return config.getInteger("stat." + key + ".slot");
            case "Fall":

                return config.getInteger("stat." + key + ".slot");
            case "Farm":

                return config.getInteger("stat." + key + ".slot");
            case "Mine":

                return config.getInteger("stat." + key + ".slot");
            case "Speed":

                return config.getInteger("stat." + key + ".slot");
            case "Attack_Damage":

                return config.getInteger("stat." + key + ".slot");
            case "Critical_Damage":

                return config.getInteger("stat." + key + ".slot");

            case "Ranged_Attack_Damage":
                return config.getInteger("stat." + key + ".slot");
            case "Defense":
                return config.getInteger("stat." + key + ".slot");
        }
        statType = null;
        return -1;
    }

    public boolean equals(String itemname) {
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");

        if (statType != null) {
            for (String keys : section.getKeys(false)) {
                if (keys != null) {
                    if (statType.name().equalsIgnoreCase(keys)) {
                        String name = ChatColor.translateAlternateColorCodes('&', config.getString("stat." + keys + ".name"));
                        return name.equalsIgnoreCase(itemname);
                    }
                }
            }
        }
        return false;
    }

    public void list() {
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");

        player.sendMessage("[ 스텟 종류 ]");
        for (String keys : section.getKeys(false)) {
            if (!keys.equalsIgnoreCase("point")) {
                int upgrade = config.getInteger("stat." + keys + ".upgrade");
                player.sendMessage(ChatColor.GRAY + "    ➥ " +
                        ChatColor.GOLD + " 스텟 {" + ChatColor.GRAY + keys + ChatColor.GOLD + "} 강화 {" + ChatColor.WHITE + upgrade + "§6} ");
            }
        }
    }


    public void test(StatData data) {
        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");

        player.sendMessage("[ 스텟 종류 ]");
        for (String keys : section.getKeys(false)) {
            if (!keys.equalsIgnoreCase("point")) {
                data.getConfig().setDouble("stat." + keys, 0);
            }
        }
    }


    public void setUpgrade(double upgrade, StatType key) {

        config.setDouble("stat." + key + ".upgrade", config.getDouble("stat." + key + ".upgrade") + upgrade);
        config.saveConfig();
    }

    public double getUpgradeKey(String key) {
        return config.getDouble("stat." + key + ".upgrade");
    }

    public int getUpgrade() {
        return config.getInteger("stat." + statType.name() + ".upgrade");
    }

    public Config getConfig() {
        return config;
    }

    public int getDefaultPoint() {
        return config.getInteger("stat.point");
    }
}

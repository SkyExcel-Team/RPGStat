package git.skyexcel.me.data.stat;

import de.tr7zw.nbtapi.NBTItem;
import git.skyexcel.me.RPGStatSystem;
import git.skyexcel.me.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
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
            case MAX_HEALTH:
                setValue("stat." + statType.name(), value);
                break;
            case FALL:
                setValue("stat." + statType.name(), value);
                break;
            case FARM:
                setValue("stat." + statType.name(), value);
                break;
            case MINE:
                setValue("stat." + statType.name(), value);
                break;
            case FISH:
                setValue("stat." + statType.name(), value);
                break;

            case DEFENSE:
                setValue("stat." + statType.name(), value);
                break;

            case SPEED:
                setValue("stat." + statType.name(), value);
                break;
            case ATTACK_DAMAGE:
                setValue("stat." + statType.name(), value);
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

        if (config.getConfig().get("stat." + key + ".upgrade") == null)
            config.setDouble("stat." + key + ".upgrade", 1);
        if (config.getConfig().get("stat." + key + ".limit") == null) {
            config.setDouble("stat." + key + ".limit", 0);
        } else {
            config.setDouble("stat." + key + ".limit", getLimitKey(key.name()));
        }

        if(config.getConfig().get("stat." + key + ".item") == null){
            config.getConfig().set("stat." + key + ".item", item.getItem());
        } else {
            ItemStack original = getItems(key.name());
            if(original.hasItemMeta()){
                ItemMeta meta = getItems(key.name()).getItemMeta();
                item.getItem().setItemMeta(meta);
                config.getConfig().set("stat." + key + ".item", item.getItem());
            }
        }


        config.saveConfig();
    }


    public String translate(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");
        String result = config.getString("stat." + key + ".name");
        switch (key) {
            case "MAX_HEALTH":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "FALL":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "FARM":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "MINE":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "SPEED":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "ATTACK_DAMAGE":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "CRITICAL_DAMAGE":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "RANGED_ATTACK_DAMAGE":
                return ChatColor.translateAlternateColorCodes('&', result);
            case "DEFENSE":
                return ChatColor.translateAlternateColorCodes('&', result);
        }

        return null;
    }

    public ItemStack getItems(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");
        ItemStack item;
        switch (key) {
            case "MAX_HEALTH":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");

                return item;
            case "FALL":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "FARM":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "MINE":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "SPEED":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "ATTACK_DAMAGE":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "CRITICAL_DAMAGE":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;

            case "RANGED_ATTACK_DAMAGE":
                item = (ItemStack) config.getConfig().get("stat." + key + ".item");
                return item;
            case "DEFENSE":
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
            case "MAX_HEALTH":

                return config.getInteger("stat." + key + ".slot");
            case "FALL":

                return config.getInteger("stat." + key + ".slot");
            case "FARM":

                return config.getInteger("stat." + key + ".slot");
            case "MINE":

                return config.getInteger("stat." + key + ".slot");
            case "SPEED":

                return config.getInteger("stat." + key + ".slot");
            case "ATTACK_DAMAGE":

                return config.getInteger("stat." + key + ".slot");
            case "CRITICAL_DAMAGE":

                return config.getInteger("stat." + key + ".slot");

            case "RANGED_ATTACK_DAMAGE":
                return config.getInteger("stat." + key + ".slot");
            case "DEFENSE":
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
                int limit = config.getInteger("stat." + keys + ".limit");
                player.sendMessage(
                        ChatColor.GOLD + " 스텟 {" + ChatColor.GRAY + keys + ChatColor.GOLD + "} 강화 {" + ChatColor.WHITE + upgrade + "§6} 한도 : " + limit);
            }
        }
    }

    public void addLore(StatType type, String msg) {
        ItemStack item = getItems(statType.name());
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            if (item.hasItemMeta()){
                List<String> lore = meta.getLore();
                if(msg.equalsIgnoreCase(null)){
                    lore.add("");
                    meta.setLore(lore);
                } else{
                    lore.add(msg);
                    meta.setLore(lore);
                }

            } else{
                List<String> lore = new ArrayList<>();
                if(msg.equalsIgnoreCase(null)){
                    lore.add("");
                    meta.setLore(lore);
                } else{
                    lore.add(msg);
                    meta.setLore(lore);
                }
            }
            item.setItemMeta(meta);
        }
        setItem(getItems(type.name()).getItemMeta().getDisplayName(),getItems(statType.name()).getAmount(),statType);
    }

    public void removeLore(StatType type, int line) {
        ItemStack item = getItems(statType.name());
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            if (item.hasItemMeta()){
                List<String> lore = meta.getLore();
                if(lore.size() < line){
                    player.sendMessage(ChatColor.RED + " 해당 줄은 로어에 존재하지 않습니다.");
                } else{
                    lore.remove(line);
                    meta.setLore(lore);
                    player.sendMessage("성공적으로 로어를 삭제 하였습니다!");
                }

            }
            item.setItemMeta(meta);
        }
        setItem(getItems(type.name()).getItemMeta().getDisplayName(),getItems(statType.name()).getAmount(),statType);
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

    public void setLimit(double upgrade, StatType key) {

        config.setDouble("stat." + key + ".limit", config.getDouble("stat." + key + ".limit") + upgrade);
        config.saveConfig();
    }

    public double getUpgradeKey(String key) {
        return config.getDouble("stat." + key + ".upgrade");
    }


    public double getLimitKey(String key) {
        return config.getDouble("stat." + key + ".limit");
    }

    public double getUpgrade() {
        return config.getDouble("stat." + statType.name() + ".upgrade");
    }

    public Config getConfig() {
        return config;
    }

    public int getDefaultPoint() {
        return config.getInteger("stat.point");
    }
}

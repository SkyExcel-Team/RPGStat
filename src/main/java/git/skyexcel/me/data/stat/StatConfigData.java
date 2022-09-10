package git.skyexcel.me.data.stat;

import de.tr7zw.nbtapi.NBTItem;
import git.skyexcel.me.data.Data;
import git.skyexcel.me.main.RPGStatSystem;
import git.skyexcel.me.data.Config;
import git.skyexcel.me.data.gui.item.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.Arrays;
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
        this.statType = stat;
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

    public void setItem(String name, int slot, String key) {
        this.name = name;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem item = new NBTItem(itemStack);
        item.applyNBT(itemStack);

        config.setString("stat." + key + ".name", "");
        config.setInteger("stat." + key + ".slot", slot);
        config.getConfig().set("stat." + key + ".item", player.getInventory().getItemInMainHand());
        config.saveConfig();
    }


    public String translate(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");

        switch (key) {
            case "Max_Health":
                return config.getString("stat." + key + ".name");
            case "Fall":
                return config.getString("stat." + key + ".name");
            case "Farm":
                return config.getString("stat." + key + ".name");
            case "Mine":
                return config.getString("stat." + key + ".name");
            case "Speed":
                return config.getString("stat." + key + ".name");
            case "Attack_Damage":
                return config.getString("stat." + key + ".name");
            case "Critical_Damage":
                return config.getString("stat." + key + ".name");

            case "Ranged_Attack_Damage":
                return config.getString("stat." + key + ".name");
            case "Defense":

                return config.getString("stat." + key + ".name");
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

    public void listGUI() {
        Inventory inv = Bukkit.createInventory(null, 27, "스텟 설정");

        UtilItem.newItem(Data.addStat, Material.ENDER_EYE, 1, Arrays.asList(""), 10, inv);
        UtilItem.newItem(Data.upgradeStat, Material.EMERALD, 1, Arrays.asList(""), 12, inv);
        UtilItem.newItem(Data.limitStat, Material.MUSIC_DISC_STAL, 1, Arrays.asList(""), 14, inv);
        UtilItem.newItem(Data.editGUI, Material.NAME_TAG, 1, Arrays.asList(""), 16, inv);
        player.openInventory(inv);
        Data.isAddStast.put(player.getUniqueId(), inv);

    }

    public void statGUI() {

        ConfigurationSection section = config.getConfig().getConfigurationSection("stat");
        Inventory inv = Bukkit.createInventory(null, 27, player.getDisplayName() + ChatColor.GOLD + " 님의 스탯");

        for (String keys : section.getKeys(false)) {


            String name = translate(keys);
            player.sendMessage(name);

            int slot = getSlots(keys);
            ItemStack item = getItems(keys);

            if (item != null) {
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(name);
                item.setItemMeta(meta);
                inv.setItem(slot, item);
            }
        }
        player.openInventory(inv);
    }

    public Inventory statListGUI() {
        Inventory inv = Bukkit.createInventory(null, 54, Data.addStat);
        UtilItem.newItem("최대 체력", Material.APPLE, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 19, inv);

        UtilItem.newItem("공격력", Material.DIAMOND_SWORD, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Attack_Damage.name()), 20, inv);

        UtilItem.newItem("치명타", Material.BLAZE_POWDER, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Critical_Damage.name()), 21, inv);

        UtilItem.newItem("낚시", Material.FISHING_ROD, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 22, inv);

        UtilItem.newItem("원거리 공격력", Material.BOW, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 23, inv);

        UtilItem.newItem("방어력", Material.DIAMOND_CHESTPLATE, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 24, inv);

        UtilItem.newItem("농사", Material.DIAMOND_HOE, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 25, inv);

        UtilItem.newItem("체굴", Material.DIAMOND_AXE, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 28, inv);

        UtilItem.newItem("스피드", Material.DIAMOND_BOOTS, 1, Arrays.asList
                (ChatColor.WHITE + "타입 : " + StatType.Max_Health.name()), 29, inv);

        player.openInventory(inv);
        return inv;
    }

    public Config getConfig() {
        return config;
    }
}

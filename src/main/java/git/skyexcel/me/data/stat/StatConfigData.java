package git.skyexcel.me.data.stat;

import data.Config;
import de.tr7zw.nbtapi.NBTItem;
import git.skyexcel.me.data.gui.item.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;

public class StatConfigData implements Stat{

    private StatType statType;
    private String name;
    private double value;

    private Config config;

    private Player player;

    public StatConfigData(  Player player) {

        this.player = player;
        this.config = new Config("config");
    }

    @Override
    public Stat addModifier(StatType stat) {
        if(this.statType != null){
            this.statType = stat;
            return this;
        }
        return null;
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
    private void setValue(String path, double value){
        if(config.getConfig().get(path) == null)
            config.setDouble(path,value);
        config.setDouble(path,config.getDouble(path) + value);
    }

    public void setName(String name,String key){
        this.name = name;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem item = new NBTItem(itemStack);
        item.applyNBT(itemStack);

        config.setString(key + ".name",name);
        config.getConfig().set(key + ".item", player.getInventory().getItemInMainHand());
        config.saveConfig();
    }
    public void addItem(){

    }
    public String translate(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");
        if(config.getConfig().get(key + ".name") == null){
            switch (key) {
                case "Max_Health":
                    return config.getString(key + ".name");
                case "Fall":
                    return config.getString(key + ".name");
                case "Farm":
                    return config.getString(key + ".name");
                case "Mine":
                    return config.getString(key + ".name");
                case "Speed":
                    return config.getString(key + ".name");
                case "Attack_Damage":
                    return config.getString(key + ".name");
                case "Critical_Damage":
                    return config.getString(key + ".name");

                case "Ranged_Attack_Damage":
                    return config.getString(key + ".name");
            }
        } else{
            statType= null;
        }
        return null;
    }
    public ItemStack getItems(String key) {
        Objects.requireNonNull(config, "Config is null!");
        Objects.requireNonNull(key, "key is null!");
        ItemStack item;
        switch (key) {
            case "Max_Health":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;
            case "Fall":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;
            case "Farm":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;
            case "Mine":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;
            case "Speed":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;
            case "Attack_Damage":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;
            case "Critical_Damage":
                item = (ItemStack) config.getConfig().get(key + ".name");
                return item;

            case "Ranged_Attack_Damage":
                item = (ItemStack) config.getConfig().get(key + ".name");
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

                return  config.getInteger(key + ".slot");
            case "Fall":

                return  config.getInteger(key + ".slot");
            case "Farm":

                return  config.getInteger(key + ".slot");
            case "Mine":

                return  config.getInteger(key + ".slot");
            case "Speed":

                return  config.getInteger(key + ".slot");
            case "Attack_Damage":

                return  config.getInteger(key + ".slot");
            case "Critical_Damage":

                return  config.getInteger(key + ".slot");

            case "Ranged_Attack_Damage":

                return  config.getInteger(key + ".slot");
        }
        statType = null;
        return -1;
    }

    public void listGUI(){
        Inventory inv = Bukkit.createInventory(null,54, name);

        UtilItem.newItem("스텟종류", Material.EYE_OF_ENDER,1, Arrays.asList(""),1,inv);
        UtilItem.newItem("강화여부", Material.EMERALD_ORE,1, Arrays.asList(""),2,inv);
        UtilItem.newItem("한도조정", Material.APPLE,1, Arrays.asList(""),3,inv);
        UtilItem.newItem("로어설정", Material.NAME_TAG,1, Arrays.asList(""),4,inv);
        player.openInventory(inv);
    }
    public void statListGUI(){
        Inventory inv = Bukkit.createInventory(null,54,"");
        UtilItem.newItem("최대 체력", Material.APPLE,1, Arrays.asList(""),20,inv);
        UtilItem.newItem("공격력", Material.DIAMOND_SWORD,1, Arrays.asList(""),21,inv);
        UtilItem.newItem("치명타", Material.APPLE,1, Arrays.asList(""),22,inv);
        UtilItem.newItem("원거리 공격력", Material.APPLE,1, Arrays.asList(""),23,inv);
        UtilItem.newItem("방어력", Material.APPLE,1, Arrays.asList(""),24,inv);
        UtilItem.newItem("농사", Material.APPLE,1, Arrays.asList(""),25,inv);
        UtilItem.newItem("체굴", Material.APPLE,1, Arrays.asList(""),25,inv);
        UtilItem.newItem("스피드", Material.APPLE,1, Arrays.asList(""),25,inv);
        UtilItem.newItem("농사", Material.APPLE,1, Arrays.asList(""),25,inv);
        player.openInventory(inv);
    }

    public Config getConfig() {
        return config;
    }
}

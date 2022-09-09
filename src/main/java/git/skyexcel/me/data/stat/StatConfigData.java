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

public class StatConfigData implements Stat{

    private StatType type;
    private String name;
    private double value;

    private Config config;

    private Player player;

    public StatConfigData(String name, Player player) {
        this.name = name;
        this.player = player;
        this.config = new Config("config");
    }

    @Override
    public Stat addModifier(StatType stat) {
        this.type = stat;
        return this;
    }

    @Override
    public void setStat(double value) {

    }


    public void setName(String name,String path){
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem item = new NBTItem(itemStack);
        item.applyNBT(itemStack);

        config.setString(path + ".name",name);
        config.getConfig().set(path + ".item", player.getInventory().getItemInMainHand());
        config.saveConfig();
    }
    public void addItem(){

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

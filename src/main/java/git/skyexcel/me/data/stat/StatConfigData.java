package git.skyexcel.me.data.stat;

import data.Config;
import data.Item.NBTData;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StatConfigData implements Stat{

    private StatType type;
    private String name;
    private double value;

    private Config config;

    private Player player;

    public StatConfigData(Player player) {
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


    public Config getConfig() {
        return config;
    }
}

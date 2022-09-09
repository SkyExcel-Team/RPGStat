package git.skyexcel.me.data.gui;

import git.skyexcel.me.data.gui.item.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class GUI {
    private Player player;

    public GUI(Player player) {
        this.player = player;
    }
    public void open(){
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
}

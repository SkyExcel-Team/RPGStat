package git.skyexcel.me.event;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.main.RPGStatSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (event.getInventory() != null) {
            if (item != null) {
                if (item.hasItemMeta()) {
                    StatData data = new StatData(player);
                    StatConfigData config = new StatConfigData();
                    config.setPlayer(player);
                    String name = item.getItemMeta().getDisplayName();
                    String display = player.getDisplayName() + ChatColor.GOLD + " 님의 스탯";

                    if (event.getView().getTitle().equalsIgnoreCase(display)) {
                        if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Attack_Damage).getName()))) {
                            data.addModifier(StatType.Attack_Damage).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Ranged_Attack_Damage).getName()))) {
                            data.addModifier(StatType.Ranged_Attack_Damage).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Defense).getName()))) {
                            data.addModifier(StatType.Defense).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Max_Health).getName()))) {
                            data.addModifier(StatType.Max_Health).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Critical_Damage).getName()))) {
                            data.addModifier(StatType.Critical_Damage).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Farm).getName()))) {
                            data.addModifier(StatType.Farm).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Fish).getName()))) {
                            data.addModifier(StatType.Fish).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Mine).getName()))) {
                            data.addModifier(StatType.Mine).setStat(1);
                        } else if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(StatType.Speed).getName()))) {
                            data.addModifier(StatType.Speed).setStat(1);
                        }
                        event.setCancelled(true);

                    } else if (event.getView().getTitle().equalsIgnoreCase("스텟 설정")) {
                        if (name.equalsIgnoreCase("§fGUI 설정")) {
                            config.statGUI(data);
                        }
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equalsIgnoreCase(Data.addStat)) {
            if (Data.isAddStast.containsKey(player.getUniqueId())) {
                Bukkit.getScheduler().runTask(RPGStatSystem.plugin, new Runnable() {
                    @Override
                    public void run() {
                        player.openInventory(Data.isAddStast.get(player.getUniqueId()));
                    }
                });

            }
        }
    }

    public boolean equals(StatConfigData config, StatType type, String name) {
        if (name.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', config.addModifier(type).getName()))) {
            return true;
        }
        return false;
    }
}

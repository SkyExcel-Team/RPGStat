package git.skyexcel.me.event;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.GUI;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.main.RPGStatSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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

                    if (event.getView().getTitle().equalsIgnoreCase(display)) { //스텟 증가
                        if (config.addModifier(StatType.Attack_Damage).equals(name)) { //어택  아이템을 클릭 했을 경우
                            data.addModifier(StatType.Attack_Damage).setStat(1); // 어택 스탯을 1 올린다.
                        } else if (config.addModifier(StatType.Defense).equals(name)) {
                            data.addModifier(StatType.Defense).setStat(1);
                        } else if (config.addModifier(StatType.Max_Health).equals(name)) {
                            data.addModifier(StatType.Max_Health).setStat(1);
                        } else if (config.addModifier(StatType.Ranged_Attack_Damage).equals(name)) {
                            data.addModifier(StatType.Ranged_Attack_Damage).setStat(1);
                        } else if (config.addModifier(StatType.Mine).equals(name)) {
                            data.addModifier(StatType.Mine).setStat(1);
                        } else if (config.addModifier(StatType.Farm).equals(name)) {
                            data.addModifier(StatType.Farm).setStat(1);
                        } else if (config.addModifier(StatType.Fish).equals(name)) {
                            data.addModifier(StatType.Fish).setStat(1);
                        } else if (config.addModifier(StatType.Speed).equals(name)) {
                            data.addModifier(StatType.Speed).setStat(1);
                        } else if (config.addModifier(StatType.Critical_Damage).equals(name)) {
                            data.addModifier(StatType.Critical_Damage).setStat(1);
                        } else if (config.addModifier(StatType.Fall).equals(name)) {
                            data.addModifier(StatType.Fall).setStat(1);
                        }

                        event.setCancelled(true);
                        
                    } else if (event.getView().getTitle().equalsIgnoreCase("스텟 설정")) {
                        if (name.equalsIgnoreCase("§fGUI 설정")) {
                            GUI.editGUI(config, data, player);
                        }
                        switch (name) {
                            case Data.upgradeStat:
                                GUI.UpgradeGUI(player);
                                break;
                            case Data.limitStat:
                                GUI.LimitGUI(player);
                                break;

                        }
                        event.setCancelled(true);
                    } else if (event.getView().getTitle().equalsIgnoreCase(Data.upgradeStat)) {
                        switch (event.getSlot()) {
                            case 18: // + 0.01
                                config.setUpgrade(0.01,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;
                            case 19: // + 0.1
                                config.setUpgrade(0.1,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;

                            case 20: // + 1
                                config.setUpgrade(1,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;

                            case 21: // + 10
                                config.setUpgrade(10,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;

                            case 23: // - 0.01
                                config.setUpgrade(-0.01,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;
                            case 24: // - 0.1
                                config.setUpgrade(-0.1,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;

                            case 25: // - 1
                                config.setUpgrade(-1,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;

                            case 26: // - 10
                                config.setUpgrade(-10,Data.type.get(player.getUniqueId()));
                                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING,1,1);
                                break;
                        }

                        event.setCancelled(true);
                    } else if (event.getView().getTitle().equalsIgnoreCase(Data.limitStat)) {

                        event.setCancelled(true);
                    } else if (event.getView().getTitle().equalsIgnoreCase(Data.editGUI)) {
                        if (event.isShiftClick()) {

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
}

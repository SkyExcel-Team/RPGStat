package git.skyexcel.me.event;

import git.skyexcel.me.RPGStatSystem;
import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.GUI;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
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
                        if (config.addModifier(StatType.ATTACK_DAMAGE).equals(name)) { //어택  아이템을 클릭 했을 경우
                            data.addModifier(StatType.ATTACK_DAMAGE).setStat(1); // 어택 스탯을 1 올린다.
                        } else if (config.addModifier(StatType.DEFENSE).equals(name)) {
                            data.addModifier(StatType.DEFENSE).setStat(1);
                        } else if (config.addModifier(StatType.MAX_HEALTH).equals(name)) {
                            data.addModifier(StatType.MAX_HEALTH).setStat(1);
                        } else if (config.addModifier(StatType.RANGED_ATTACK_DAMAGE).equals(name)) {
                            data.addModifier(StatType.RANGED_ATTACK_DAMAGE).setStat(1);
                        } else if (config.addModifier(StatType.MINE).equals(name)) {
                            data.addModifier(StatType.MINE).setStat(1);
                        } else if (config.addModifier(StatType.FARM).equals(name)) {
                            data.addModifier(StatType.FARM).setStat(1);
                        } else if (config.addModifier(StatType.FISH).equals(name)) {
                            data.addModifier(StatType.FISH).setStat(1);
                        } else if (config.addModifier(StatType.SPEED).equals(name)) {
                            data.addModifier(StatType.SPEED).setStat(1);
                        } else if (config.addModifier(StatType.CRITICAL_DAMAGE).equals(name)) {
                            data.addModifier(StatType.CRITICAL_DAMAGE).setStat(1);
                        } else if (config.addModifier(StatType.FALL).equals(name)) {
                            data.addModifier(StatType.FALL).setStat(1);
                        }

                        event.setCancelled(true);

                    } else if (event.getView().getTitle().equalsIgnoreCase("스텟 설정")) {

                        switch (name) {
                            case Data.upgradeStat:
                                GUI.upgradeGUI(player);
                                break;
                            case Data.limitStat:
                                GUI.limitGUI(player);
                                break;

                        }
                        event.setCancelled(true);
                    } else if (event.getView().getTitle().equalsIgnoreCase(Data.upgradeStat)) {
                        switch (event.getSlot()) {
                            case 18: // + 0.01
                                config.setUpgrade(0.01, Data.type.get(player.getUniqueId()));
                                break;
                            case 19: // + 0.1
                                config.setUpgrade(0.1, Data.type.get(player.getUniqueId()));
                                break;

                            case 20: // + 1
                                config.setUpgrade(1, Data.type.get(player.getUniqueId()));
                                break;

                            case 21: // + 10
                                config.setUpgrade(10, Data.type.get(player.getUniqueId()));
                                break;

                            case 23: // - 0.01
                                config.setUpgrade(-0.01, Data.type.get(player.getUniqueId()));
                                break;
                            case 24: // - 0.1
                                config.setUpgrade(-0.1, Data.type.get(player.getUniqueId()));
                                break;

                            case 25: // - 1
                                config.setUpgrade(-1, Data.type.get(player.getUniqueId()));
                                break;

                            case 26: // - 10
                                config.setUpgrade(-10, Data.type.get(player.getUniqueId()));
                                break;
                        }

                        event.setCancelled(true);
                    } else if (event.getView().getTitle().equalsIgnoreCase(Data.limitStat)) {
                        switch (event.getSlot()) {

                            case 21: // + 100
                                config.setLimit(1, Data.type.get(player.getUniqueId()));
                                break;

                            case 20: // +  10
                                config.setLimit(10, Data.type.get(player.getUniqueId()));
                                break;

                            case 19: // + 1
                                config.setLimit(100, Data.type.get(player.getUniqueId()));
                                break;

                            case 23: // - 100
                                config.setLimit(-1, Data.type.get(player.getUniqueId()));
                                break;
                            case 24: // - 10
                                config.setLimit(-10, Data.type.get(player.getUniqueId()));
                                break;

                            case 25: // - 1
                                config.setLimit(-100, Data.type.get(player.getUniqueId()));
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

        String display = player.getDisplayName() + ChatColor.GOLD + " 님의 스탯";


//        if (event.getView().getTitle().equalsIgnoreCase(Data.addStat)) {
//            if (Data.isAddStast.containsKey(player.getUniqueId())) {
//                Bukkit.getScheduler().runTask(RPGStatSystem.plugin, new Runnable() {
//                    @Override
//                    public void run() {
//                        player.openInventory(Data.isAddStast.get(player.getUniqueId()));
//                    }
//                });
//
//            }
        if (event.getView().getTitle().equalsIgnoreCase(display)) {
            cancel(player);

        } else if (event.getView().getTitle().equalsIgnoreCase(Data.upgradeStat)) {
            Bukkit.getScheduler().runTask(RPGStatSystem.plugin, new Runnable() {
                @Override
                public void run() {
                    GUI.listGUI(player);
                }
            });
            Data.statTask.get(player.getUniqueId()).cancel();
            Data.statTask.remove(player.getUniqueId());
        } else if (event.getView().getTitle().equalsIgnoreCase(Data.limitStat)) {
            Bukkit.getScheduler().runTask(RPGStatSystem.plugin, new Runnable() {
                @Override
                public void run() {
                    GUI.listGUI(player);
                }
            });
            Data.statTask.get(player.getUniqueId()).cancel();
            Data.statTask.remove(player.getUniqueId());
        }


    }


    public boolean open(InventoryCloseEvent event, String data, Inventory inv) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equalsIgnoreCase(data)) {

            Bukkit.getScheduler().runTask(RPGStatSystem.plugin, new Runnable() {
                @Override
                public void run() {

                    if (inv != null)
                        player.openInventory(inv);
                }
            });
            cancel(player);
            return true;
        }
        return false;
    }

    public void cancel(Player player) {
        if (Data.statTask.containsKey(player.getUniqueId())) {

            Data.statTask.get(player.getUniqueId()).cancel();
            Data.statTask.remove(player.getUniqueId());
        }
    }
}

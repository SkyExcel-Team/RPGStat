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
                String name = item.getItemMeta().getDisplayName();
                String display = player.getDisplayName() + ChatColor.GOLD + " 님의 스탯";
                if(event.getView().getTitle().equalsIgnoreCase(display)){

                    StatData data = new StatData(player);
                    switch (name) {
                        case Data.Health:
                            data.addModifier(StatType.Max_Health).setStat(1);

                            player.sendMessage("성공적으로 체력을 설정하였습니다!" );
                            break;
                        case Data.Attack_Damage:
                            data.addModifier(StatType.Attack_Damage).setStat(50);
                            player.sendMessage("성공적으로 공격력을 설정하였습니다!");
                            break;
                        case Data.Critical:
                            data.addModifier(StatType.Critical_Damage).setStat(50);
                            player.sendMessage("성공적으로 공격력을 설정하였습니다!");
                            break;
                        case Data.Fish:
                            data.addModifier(StatType.Fish).setStat(50);
                            player.sendMessage("성공적으로 낚시를 설정하였습니다!");
                            break;
                        case Data.Mine:
                            data.addModifier(StatType.Mine).setStat(50);
                            player.sendMessage("성공적으로 광질을 설정하였습니다!");
                            break;
                        case Data.Farm:
                            data.addModifier(StatType.Farm).setStat(50);
                            player.sendMessage("성공적으로 농사를 설정하였습니다!");
                            break;
                        case Data.Defense:
                            data.addModifier(StatType.Defense).setStat(50);
                            player.sendMessage("성공적으로 방어력을 설정하였습니다!");
                            break;
                        case Data.Speed:
                            data.addModifier(StatType.Speed).setStat(50);
                            player.sendMessage("성공적으로 스피드를 설정하였습니다!");
                            break;
                    }
                    event.setCancelled(true);
                }
//                switch (event.getView().getTitle()) {
//                    case display:
//                        StatData data = new StatData(player);
//                        StatConfigData Configdata;
//                        switch (name) {
//                            case Data.Health:
//                                data.addModifier(StatType.Max_Health).setStat(50);
//                                break;
//                            case Data.Attack_Damage:
//                                data.addModifier(StatType.Attack_Damage).setStat(50);
//                                player.sendMessage("성공적으로 공격력을 설정하였습니다!");
//                                break;
//                            case Data.Critical:
//                                data.addModifier(StatType.Critical_Damage).setStat(50);
//                                player.sendMessage("성공적으로 공격력을 설정하였습니다!");
//                                break;
//                            case Data.Fish:
//                                data.addModifier(StatType.Fish).setStat(50);
//                                player.sendMessage("성공적으로 낚시를 설정하였습니다!");
//                                break;
//                            case Data.Mine:
//                                data.addModifier(StatType.Mine).setStat(50);
//                                player.sendMessage("성공적으로 광질을 설정하였습니다!");
//                                break;
//                            case Data.Farm:
//                                data.addModifier(StatType.Farm).setStat(50);
//                                player.sendMessage("성공적으로 농사를 설정하였습니다!");
//                                break;
//                            case Data.Defense:
//                                data.addModifier(StatType.Defense).setStat(50);
//                                player.sendMessage("성공적으로 방어력을 설정하였습니다!");
//                                break;
//                            case Data.Speed:
//                                data.addModifier(StatType.Speed).setStat(50);
//                                player.sendMessage("성공적으로 스피드를 설정하였습니다!");
//                                break;
//                        }
//                        event.setCancelled(true);
//                        break;
//                    case "스텟 설정":
//                        Configdata = new StatConfigData();
//                        Configdata.setPlayer(player.getPlayer());
//                        Configdata.statListGUI();
//
//                        break;
//                    case Data.editGUI:
//                        Configdata = new StatConfigData();
//                        Configdata.setPlayer(player.getPlayer());
//
//                        event.setCancelled(true);
//                        break;
//                }
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

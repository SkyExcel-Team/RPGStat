package git.skyexcel.me.event;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import git.skyexcel.me.main.RPGStatSystem;
import org.bukkit.Bukkit;
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
                switch (event.getView().getTitle()) {
                    case Data.addStat:
                        StatData data = new StatData(player);
                        switch (name) {
                            case "최대 체력":
                                data.addModifier(StatType.Max_Health).setStat(50);
                                break;
                            case "공격력":
                                data.addModifier(StatType.Attack_Damage).setStat(50);
                                player.sendMessage("성공적으로 공격력을 설정하였습니다!");
                                break;
                        }
                        event.setCancelled(true);
                        break;
                    case "스텟 설정":
                        StatConfigData Configdata = new StatConfigData();

                        Configdata.setPlayer(player.getPlayer());
                        Configdata.statListGUI();

                        break;
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

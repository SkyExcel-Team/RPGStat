package git.skyexcel.me.event;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.stat.StatConfigData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatEvent implements Listener {
    @EventHandler
    public void chat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String chat = event.getMessage();

        if(Data.name.containsKey(player.getUniqueId())){
            String name = Data.name.get(player.getUniqueId());
            StatConfigData data = new StatConfigData();
            data.setPlayer(player);
            if(!Data.line.containsKey(player.getUniqueId())){
                try{
                    int line = Integer.parseInt(chat);
                    Data.line.put(player.getUniqueId(),line);
                    player.sendMessage("> 로어를 입력해 주세요!");
                } catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "> error : " + ChatColor.GRAY + "숫자로 입력해 주세요!");
                }
            } else if(!Data.lore.containsKey(player.getUniqueId())){
                List<String> lore = Data.lore.get(player.getUniqueId());
                lore.set(Data.line.get(player.getUniqueId()),chat);
                Data.lore.put(player.getUniqueId(),lore);
                player.sendMessage(ChatColor.GREEN + "> success : " + ChatColor.GRAY + "적용되었습니다!");

            }
        }
    }
}

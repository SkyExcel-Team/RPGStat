package git.skyexcel.me.cmd;

import git.skyexcel.me.data.stat.StatData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatCheckCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            StatData data = new StatData(target);
            data.listStat(player);
        } else{
            player.sendMessage("/스텟보기 <유저> : 유저의 현재 찍혀진 스텟량들을 확인하는 명령어\n");
        }

        return false;
    }
}

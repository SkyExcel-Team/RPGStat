package git.skyexcel.me.cmd;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.Stat;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatEditCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            StatConfigData stat;
            String name;
            if(args.length > 0){

                switch (args[0]){

                    case "열기":
                        if(args.length > 1){
                            name = args[1];
                            stat = new StatConfigData(name,player);

                            Data.name.put(player.getUniqueId(),name);
                        }
                        break;
                    case "추가":

                        break;
                    case "이름수정":

                        break;

                    case "아이템":

                        break;


                    case "리스트":

                        break;
                }
            }

        }

        return false;
    }
}

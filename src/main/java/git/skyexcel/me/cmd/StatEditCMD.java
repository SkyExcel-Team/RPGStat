package git.skyexcel.me.cmd;

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
            if(args.length > 1){

                switch (args[0]){

                    case "열기":

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

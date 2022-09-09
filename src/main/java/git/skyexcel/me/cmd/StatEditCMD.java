package git.skyexcel.me.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatEditCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length > 1){
                switch (args[0]){
                    case "추가":

                        break;
                    case "수정":

                        break;
                    case "이름수정":

                        break;
                    case "설명문":

                        break;
                    case "설명문삭제":

                        break;
                    case "아이콘":

                        break;
                    case "한도":

                        break;
                    case "삭제":

                        break;
                    case "리스트":

                        break;
                }
            }

        }

        return false;
    }
}

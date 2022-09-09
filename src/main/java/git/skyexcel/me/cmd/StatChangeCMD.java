package git.skyexcel.me.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatChangeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            switch (args[0]){
                case "설정":

                    break;
                case "추가":

                    break;
                case "세부설정":

                    break;
                case "세부추가":

                    break;
                case "리셋":

                    break;
                case "삭제":

                    break;
            }
        }
        return false;
    }
}

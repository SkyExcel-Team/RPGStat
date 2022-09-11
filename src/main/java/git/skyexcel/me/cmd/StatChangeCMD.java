package git.skyexcel.me.cmd;

import git.skyexcel.me.data.stat.StatData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatChangeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            Player player = (Player) sender;
            switch (args[0]) {
                case "설정":
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (args.length > 2) {
                            int statpoint = Integer.parseInt(args[2]);
                            StatData data = new StatData(target);
                            data.setStatPoint(statpoint);
                        }
                    }

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

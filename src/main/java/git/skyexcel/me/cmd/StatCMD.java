package git.skyexcel.me.cmd;

import git.skyexcel.me.data.gui.GUI;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            StatData data = new StatData(player);
            StatConfigData config = new StatConfigData();
            config.setPlayer(player);

            GUI.statGUI(config,data,player);
        }
        return false;
    }
}

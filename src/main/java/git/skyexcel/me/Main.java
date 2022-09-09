package git.skyexcel.me;

import git.skyexcel.me.cmd.StatCMD;
import git.skyexcel.me.cmd.StatChangeCMD;
import git.skyexcel.me.cmd.StatCheckCMD;
import git.skyexcel.me.cmd.StatEditCMD;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static String DataPath = "data/";
    @Override
    public void onEnable() {
        super.onEnable();
        getCommand("스텟설정").setExecutor(new StatEditCMD());
        getCommand("스텟변경").setExecutor(new StatChangeCMD());
        getCommand("스텟보기").setExecutor(new StatCheckCMD());
        getCommand("스텟").setExecutor(new StatCMD());

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    public static String getDataPath(Player player){
        return DataPath + player.getUniqueId();
    }
}

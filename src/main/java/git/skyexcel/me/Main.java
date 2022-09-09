package git.skyexcel.me;

import git.skyexcel.me.cmd.StatCMD;
import git.skyexcel.me.cmd.StatChangeCMD;
import git.skyexcel.me.cmd.StatCheckCMD;
import git.skyexcel.me.cmd.StatEditCMD;
import git.skyexcel.me.data.stat.StatConfigData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static String DataPath = "data/";
    public static Plugin plugin;
    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        getCommand("스텟설정").setExecutor(new StatEditCMD());
        getCommand("스텟변경").setExecutor(new StatChangeCMD());
        getCommand("스텟보기").setExecutor(new StatCheckCMD());
        getCommand("스텟").setExecutor(new StatCMD());
        StatConfigData data = new StatConfigData();

        data.getConfig().loadDefaultPluginConfig();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    public static String getDataPath(Player player){
        return DataPath + player.getUniqueId();
    }
}

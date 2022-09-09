package git.skyexcel.me;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static String DataPath = "data/";
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    public static String getDataPath(Player player){
        return DataPath + player.getUniqueId();
    }
}

package git.skyexcel.me;



import git.skyexcel.me.cmd.StatCMD;
import git.skyexcel.me.cmd.StatChangeCMD;
import git.skyexcel.me.cmd.StatCheckCMD;
import git.skyexcel.me.cmd.StatEditCMD;
import git.skyexcel.me.data.Config;
import git.skyexcel.me.data.Data;
import git.skyexcel.me.event.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;


public class RPGStatSystem extends JavaPlugin {
    public static String DataPath = "data/";
    public static Plugin plugin;

    public static Config config;


    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        getCommand("스탯설정").setExecutor(new StatEditCMD());
        getCommand("스탯설정").setTabCompleter(new StatEditCMD());
        getCommand("스탯변경").setExecutor(new StatChangeCMD());
        getCommand("스탯보기").setExecutor(new StatCheckCMD());
        getCommand("스탯").setExecutor(new StatCMD());
        this.config = new Config("config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();
        Listener[] events = {new BlockBreak(),new DamageEvent(), new InventoryEvent(), new JoinEvent(), new LevelUpEvent(), new FishEvent()};
        PluginManager pm = Bukkit.getPluginManager();

        Arrays.stream(events).forEach(classes -> {
            pm.registerEvents(classes, this);
        });



    }

    @Override
    public void onDisable() {
        super.onDisable();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (Data.statTask.containsKey(player.getUniqueId())) {
                player.closeInventory();
            }
        });
    }

    public static String getDataPath(Player player) {
        return DataPath + player.getUniqueId();
    }
}

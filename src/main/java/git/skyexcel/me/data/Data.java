package git.skyexcel.me.data;

import git.skyexcel.me.data.stat.StatType;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Data {

    public static HashMap<UUID, Integer> line = new HashMap<>();
    public static HashMap<UUID, List<String>> lore = new HashMap<>();
    public static HashMap<UUID, String> name = new HashMap<>();

    public static HashMap<UUID, StatType> type = new HashMap<>();
    public static HashMap<UUID, Inventory> isAddStast = new HashMap<>();

    public static HashMap<UUID, BukkitRunnable> statTask = new HashMap<>();

    public final static String editGUI = "§fGUI 설정";
    public final static String addStat = "§a스텟 추가";
    public final static String upgradeStat = "§b강화 여부";
    public final static String limitStat = "§7한도 조정";

    public final static String loreStat = "§f로어 설정";
}

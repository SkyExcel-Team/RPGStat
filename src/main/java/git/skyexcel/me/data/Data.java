package git.skyexcel.me.data;

import git.skyexcel.me.data.stat.StatType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Data {

    public static HashMap<UUID, Integer> line = new HashMap<>();
    public static HashMap<UUID, List<String>> lore = new HashMap<>();
    public static HashMap<UUID, String> name = new HashMap<>();

    public static HashMap<UUID, StatType> type = new HashMap<>();
    public static HashMap<UUID, Inventory> isAddStast = new HashMap<>();


    public final static String editGUI = "§fGUI 설정";
    public final static String addStat = "§a스텟 추가";
    public final static String upgradeStat = "§b강화 여부";
    public final static String limitStat = "§7한도 조정";
    public final static String loreStat = "§f로어 설정";




    public final static String Health = "체력";

    public final static String Critical = "치명타";

    public final static String Attack_Damage = "공격력";

    public final static String Ranged_Attack_Damage = "원거리 공격력";

    public final static String Fish = "낚시";

    public final static String Defense = "방어력";

    public final static String Farm = "농사";

    public final static String Mine = "체굴";

    public final static String Speed = "스피드";

}

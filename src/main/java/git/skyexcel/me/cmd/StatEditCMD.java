package git.skyexcel.me.cmd;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.gui.GUI;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StatEditCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                StatConfigData stat;
                String name;
                if (args.length > 0) {

                    switch (args[0]) {
                        case "편집":
                            if (args.length > 1) {
                                StatType type = StatType.valueOf(args[1]);

                                stat = new StatConfigData();
                                stat.setPlayer(player);
                                Data.type.put(player.getUniqueId(), type);
                                GUI.listGUI(player);
                            }
                            break;
                        case "추가":
                            if (args.length > 1) {
                                name = args[1];
                                stat = new StatConfigData();
                                stat.setPlayer(player);
                                Data.name.put(player.getUniqueId(), name);
                                GUI.listGUI(player);
                            }
                            break;
                        case "이름수정":

                            break;

                        case "아이템":
                            if (args.length > 1) {
                                if (args.length > 2) {
                                    try {
                                        int slot = Integer.parseInt(args[1]);

                                        StatType type = StatType.valueOf(args[2]);
                                        stat = new StatConfigData();
                                        stat.setPlayer(player);
                                        stat.setItem(type.name(), slot, type);

                                        StatData data = new StatData(player);

                                        Data.name.put(player.getUniqueId(), type.name());
                                        player.sendMessage("test");

                                    } catch (NumberFormatException e) {
                                        player.sendMessage(ChatColor.RED + "> " + ChatColor.GRAY + "슬롯을 숫자로 입력해 주세요!");
                                    }
                                }
                            }
                            break;


                        case "리스트":
                            StatConfigData config = new StatConfigData();
                            config.setPlayer(player);
                            config.list();
                            break;

                    }
                } else {
                   player.sendMessage(
                           "/스텟설정 편집 <스텟종류> : 해당 스텟 설정 GUI창\n" +
                           "/스텟설정 이름수정 <스텟이름> <수정할이름> : 기존의 스텟의 이름을 바꿀때 사용\n" +
                           "/스텟설정 아이템 <슬롯위치> <스텟이름> : 기존의 스텟의 아이템 슬롯 바꿀때 사용\n" +
                           "스텟설정으로 연 GUI 창에서 아이템을 클릭후 \n" +
                           "채팅에 로어 줄과 로어를 입력하면 다시 GUI창이 열리면서 적용됨.\n" +
                           "/스텟설정 리스트 : 현재 만들어진 스텟들을 확인하는 명령어");

                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {

            result.add("이름수정");
            result.add("아이템");
            result.add("편집");
            result.add("리스트");

            return result;
        } else if (args.length == 2) {
            switch (args[0]) {
                case "이름수정":
                    for (StatType types : StatType.values()) {
                        result.add(types.name());
                    }
                    return result;

                case "아이템":
                    result.add("<슬롯 위치>");

                    return result;
                case "편집":
                    StatConfigData config = new StatConfigData();
                    ConfigurationSection section = config.getConfig().getConfig().getConfigurationSection("stat");


                    for (String keys : section.getKeys(false)) {
                        if(!keys.equalsIgnoreCase("point")){
                            result.add(keys);
                        }
                    }
                    return result;
            }
        } else if (args.length == 3) {
            switch (args[0]) {
                case "이름수정":
                    result.add("<수정할 이름>");
                    break;
                case "아이템":


                    for (StatType types : StatType.values()) {
                        result.add(types.name());
                    }
                    return result;


            }
        }

        return null;
    }
}

package git.skyexcel.me.cmd;

import git.skyexcel.me.data.Data;
import git.skyexcel.me.data.stat.StatConfigData;
import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.Stat;
import git.skyexcel.me.data.stat.StatType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
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
            StatConfigData stat;
            String name;
            if (args.length > 0) {

                switch (args[0]) {

                    case "열기":
                        if (args.length > 1) {
                            name = args[1];
                            stat = new StatConfigData();
                            stat.setPlayer(player);
                            Data.name.put(player.getUniqueId(), name);
                            stat.listGUI();
                        }
                        break;
                    case "추가":
                        if (args.length > 1) {
                            name = args[1];
                            stat = new StatConfigData();
                            stat.setPlayer(player);
                            Data.name.put(player.getUniqueId(), name);
                            stat.listGUI();
                        }
                        break;
                    case "이름수정":

                        break;

                    case "아이템":
                        if (args.length > 1) {
                            if (args.length > 2) {

                                int slot = Integer.parseInt(args[1]);
                                String type = args[2];
                                stat = new StatConfigData();
                                stat.setPlayer(player);
                                stat.setItem(type, slot,type);

                                StatData data = new StatData(player);

                                Data.name.put(player.getUniqueId(), type);
                                player.sendMessage("test");

                            }
                        }
                        break;


                    case "리스트":

                        break;
                }
            } else {
                stat = new StatConfigData();
                stat.setPlayer(player);
                stat.listGUI();

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
            result.add("포인트");
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

package git.skyexcel.me.cmd;

import git.skyexcel.me.data.stat.StatData;
import git.skyexcel.me.data.stat.StatType;
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
                            player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 스텟을 §6" + statpoint + " §a으로 설정하였습니다!");
                        }
                    }

                    break;

                case "세부설정":
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (args.length > 2) {
                            StatType type = StatType.valueOf(args[2]);

                            double statpoint = Double.parseDouble(args[3]);
                            StatData data = new StatData(target);
                            data.addModifier(type).changeStat(statpoint);
                            player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 §7" + type.name() + "§a스텟을 §6" + statpoint + " §a으로 설정하였습니다!");
                        }
                    }
                    break;
                case "세부추가":
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (args.length > 2) {
                            StatType type = StatType.valueOf(args[2]);

                            double statpoint = Double.parseDouble(args[3]);
                            StatData data = new StatData(target);
                            data.addModifier(type).addStat(statpoint);
                            player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 §7" + type.name() + "§a스텟을 §6" + statpoint + " §a만큼 추가하였습니다!");
                        }
                    }

                    break;
                case "리셋":
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayer(args[1]);

                        StatData data = new StatData(target);
                        data.collapseStat();
                        player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 스텟을 초기화하였습니다!!");
                    } else{


                        StatData data = new StatData(player);
                        data.collapseStat();
                        player.sendMessage("§a성공적으로 §f" + player.getDisplayName() + " §a님의 스텟을 초기화하였습니다!!");
                    }
                    break;
                case "삭제":
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (args.length > 2) {
                            StatType type = StatType.valueOf(args[2]);

                            double statpoint = Double.parseDouble(args[3]);
                            StatData data = new StatData(target);
                            data.addModifier(type).increaseValue("stats." + type.name(), statpoint);
                            player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 §7" + type.name() + "§a스텟을 §6" + statpoint + " §a만큼 제거하였습니다!");
                        }
                    }
                    break;

                case "초기화":
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (args.length > 2) {
                            StatType type = StatType.valueOf(args[2]);


                            StatData data = new StatData(target);
                            double statpoint = data.addModifier(type).getStat();
                            data.addModifier(type).increaseValue("stat." + type.name(), statpoint);
                            player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 §7" + type.name() + "§a스텟을 초기화 하였습니다!");
                        } else {
                            StatData data = new StatData(target);
                            data.resetStat();

                            player.sendMessage("§a성공적으로 §f" + target.getDisplayName() + " §a님의 모든 스텟을 초기화 하였습니다!");
                        }


                    }
                    break;

            }
        } else {
            sender.sendMessage(
                    "/스텟변경 설정 <유저> <숫자> : 유저의 스텟포인트의 양을 지정한 숫자만큼으로 바꾸는 명령어\n" +
                            "/스텟변경 세부설정 <유저> <스텟이름> <숫자> : 유저가 찍어놓은 스텟의 스텟량을 숫자만큼 바꾸는 명령어\n" +
                            "/스텟변경 세부추가 <유저> <스텟이름> <숫자> : 유저가 찍어놓은 스텟의 스텟량을 숫자만큼 추가해주는 명령어\n" +
                            "/스텟변경 리셋 <유저> <스텟이름> : 유저의 <스텟이름> 에 적힌 스텟의 스텟량을 0으로 초기화 시키는 명령어,\n" +
                            " 대신 초기화시킨 스텟량을 스텟포인트로 전환해줌\n" +
                            "\n" +
                            "/스텟변경 초기화 <유저> <스텟이름> : 위의 명령어와 다르게 스텟량을 0으로 초기화만 시키는 명령어\n" +
                            "/스텟변경 초기화 <유저> : 지정한 유저의 모든 스텟을 0으로 초기화 시키는 명령어, \n" +
                            "따로 스텟포인트로 전환시키지 않음");
        }
        return false;
    }
}

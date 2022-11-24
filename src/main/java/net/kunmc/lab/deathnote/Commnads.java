package net.kunmc.lab.deathnote;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

public class Commnads implements CommandExecutor, TabCompleter {

    public static boolean GAME = false;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("bn")) {
            if(args.length == 1){
                if(args[0].equals("on")){
                    if(GAME){
                        sender.sendMessage(ChatColor.RED + "[死神]プラグインはすでにonです...");
                    }else{
                        GAME = true;
                        sender.sendMessage(ChatColor.GREEN + "[死神]プラグインをonにしました...");
                    }
                }else if(args[0].equals("off")){
                    if(GAME){
                        GAME = false;
                        sender.sendMessage(ChatColor.GREEN + "[死神]プラグインをoffにしました...");
                    }else{
                        sender.sendMessage(ChatColor.RED + "[死神]プラグインはすでにoffです...");
                    }
                }else if(args[0].equals("help")){
                    sender.sendMessage(ChatColor.GOLD + "-------------コマンド一覧-------------");
                    sender.sendMessage("/bn on   : プラグインのon");
                    sender.sendMessage("/bn off  : プラグインのoff");
                    sender.sendMessage("/bn help : プラグインのコマンド一覧");
                    sender.sendMessage("/bn give <対象> : 対象にデスノートを配布");
                    sender.sendMessage(ChatColor.GOLD + "-------------コマンド一覧-------------");
                }else{
                    sender.sendMessage(ChatColor.RED + "[死神]引数が異なります.../bn help");
                }
            }else if(args.length == 2){
                if(args[0].equals("give")) {
                    if (GAME) {
                        if (args[1].equals("@r")) {
                            ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                            Random random = new Random();
                            Player player = players.get(random.nextInt(players.size()));
                            player.getLocation().getWorld().dropItem(player.getLocation(), Book.deathNote());
                            player.sendMessage(net.md_5.bungee.api.ChatColor.RED + "あなたにBANノートが与えられました...");
                            Bukkit.getLogger().info(net.md_5.bungee.api.ChatColor.GREEN + (player + "にノートを与えました@r"));
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.sendMessage(net.md_5.bungee.api.ChatColor.GOLD + "この世にBANNoteが現れました...");
                            }
                        } else if (args[1].equals("@a")) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.getLocation().getWorld().dropItem(player.getLocation(), Book.deathNote());
                                player.sendMessage(net.md_5.bungee.api.ChatColor.RED + "あなたにBANノートが与えられました...");
                                player.sendMessage(net.md_5.bungee.api.ChatColor.GOLD + "この世にBANNoteが現れました...");
                            }
                            Bukkit.getLogger().info(net.md_5.bungee.api.ChatColor.GREEN + "全員にノートを与えました@a");
                        } else {
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player != null) {
                                player.getLocation().getWorld().dropItem(player.getLocation(), Book.deathNote());
                                player.sendMessage(net.md_5.bungee.api.ChatColor.RED + "あなたにBANノートが与えられました...");
                                Bukkit.getLogger().info(net.md_5.bungee.api.ChatColor.GREEN + (player + "にノートを与えました"));
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(net.md_5.bungee.api.ChatColor.GOLD + "この世にBANNoteが現れました...");
                                }
                            } else {
                                sender.sendMessage(ChatColor.RED + "[死神]存在しないプレイヤーです...");
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "[死神]プラグインはoffです...");
                    }
                }else {
                    sender.sendMessage(ChatColor.RED + "[死神]引数が異なります.../bn help");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "[死神]引数が異なります.../bn help");
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
            if(args.length == 1){
                completions.add("on");
                completions.add("off");
                completions.add("help");
                completions.add("give");
                completions.removeIf(e -> !e.startsWith(args[0].toLowerCase(Locale.ROOT)));
            }else if(args.length == 2 && args[0].equals("give")){
                Bukkit.getOnlinePlayers().forEach(player ->{
                    completions.add(player.getName());
                });
                completions.add("@r");
                completions.add("@a");
                completions.removeIf(e -> !e.toLowerCase(Locale.ROOT).startsWith(args[1].toLowerCase(Locale.ROOT)));
            }
            return completions;
    }
}

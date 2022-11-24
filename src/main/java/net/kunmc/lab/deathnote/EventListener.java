package net.kunmc.lab.deathnote;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class EventListener implements Listener{

    @EventHandler
    public void onClick(PlayerEditBookEvent e){
        if(!Commnads.GAME) return;
        if(e.isSigning() && e.getNewBookMeta().getDisplayName().equals(ChatColor.DARK_RED+"BanNote")) {
            Player writer = e.getPlayer();
            String name = e.getNewBookMeta().getTitle();
            Player player = Bukkit.getPlayer(Objects.requireNonNull(name));
                if(player != null){
                    writer.sendMessage(ChatColor.DARK_RED + (player + "は5秒後にBANされます..."));
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            player.banPlayer(writer.getName()+"のノートの力であなたはBANされた...");
                            Bukkit.getOnlinePlayers().forEach(p -> {
                                p.sendMessage(ChatColor.RED + (player + "はノートの力で世界からBANされた..."));
                            });
                        }
                    }.runTaskLater(DeathNote.INSTANCE,100);
                }else{
                    writer.sendMessage(ChatColor.DARK_RED + "あなたは名前の記入を失敗したため5秒後にこの世界からBANされます...");
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            writer.banPlayer("あなたは間違った名前を記入したためBANされた...");
                            Bukkit.getOnlinePlayers().forEach(p -> {
                                p.sendMessage(ChatColor.RED + (writer + "(ルールの読めない敗北者)は名前の記入を失敗したためこの世界からBANされた..."));
                            });
                        }
                    }.runTaskLater(DeathNote.INSTANCE,100);
                }
        }
    }
}

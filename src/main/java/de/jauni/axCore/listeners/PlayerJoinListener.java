package de.jauni.axCore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.joinMessage(null);
        Player player = event.getPlayer();
        String userName = player.getName();
        Bukkit.broadcastMessage(ChatColor.GREEN + userName + " joined the server!");
    }
}

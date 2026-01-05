package de.jauni.axCore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.quitMessage(null);
        Player player = event.getPlayer();
        String userName = player.getName();
        Bukkit.broadcastMessage(userName + " quit the server!");
    }
}

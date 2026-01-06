package de.jauni.axCore.listeners;

import de.jauni.axCore.AxCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    AxCore reference;
    public PlayerJoinListener(AxCore main){
        this.reference = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.joinMessage(null);
        Player player = event.getPlayer();
        String userName = player.getName();
        Bukkit.broadcastMessage(ChatColor.GREEN + userName + " joined the server!");
        if(reference.isSpawnOnJoin()){
            Location spawn = player.getWorld().getSpawnLocation();
            player.teleport(spawn);
            player.sendMessage(ChatColor.GREEN + "Du wurdest zum Spawn teleportiert!");
        }
    }
}

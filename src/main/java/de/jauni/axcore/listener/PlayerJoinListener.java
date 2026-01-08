package de.jauni.axcore.listener;

import de.jauni.axcore.AxCore;
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

        if(reference.getEconomyManager().getBalance(player.getUniqueId()) == 0.0){
            reference.getEconomyManager().setBalance(player.getUniqueId(), 100.0);
            player.sendMessage(ChatColor.GREEN + "Du hast 100 erhalten!");
        }

        if(reference.isSpawnOnJoin()){
            Location spawn = player.getWorld().getSpawnLocation();
            player.teleport(spawn);
            player.sendMessage(ChatColor.GREEN + "Du wurdest zum Spawn teleportiert!");
        }
    }
}

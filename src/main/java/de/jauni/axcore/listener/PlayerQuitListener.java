package de.jauni.axcore.listener;

import de.jauni.axcore.AxCore;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    AxCore reference;
    public PlayerQuitListener(AxCore main){
        this.reference = main;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.quitMessage(null);
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        String userName = player.getName();
        if(reference.getKickedPlayers().remove(uuid)){
            return;
        }
        Bukkit.broadcastMessage(ChatColor.RED + userName + " hat den Server verlassen!");
    }
}

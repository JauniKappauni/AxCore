package de.jauni.axcore.listener;

import de.jauni.axcore.AxCore;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerJoinListener implements Listener {
    AxCore reference;
    public PlayerJoinListener(AxCore main){
        this.reference = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String userName = player.getName();

        if(reference.isSpawnOnJoin()){
            Location spawn = player.getWorld().getSpawnLocation();
            player.teleport(spawn);
            player.sendMessage(ChatColor.GREEN + "Du wurdest zum Spawn teleportiert!");
        }

        try(Connection conn = reference.getDatabaseManager().getConnection()) {
            PreparedStatement check = conn.prepareStatement("SELECT uuid FROM players WHERE uuid = ?");
            check.setString(1, player.getUniqueId().toString());
            ResultSet rs = check.executeQuery();
            if(!rs.next()){
                PreparedStatement ps = conn.prepareStatement("INSERT INTO players (uuid, name, isBanned, reason) VALUES (?, ?, ?, ?)");
                ps.setString(1, player.getUniqueId().toString());
                ps.setString(2, player.getName());
                ps.setBoolean(3, false);
                ps.setString(4, "");
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

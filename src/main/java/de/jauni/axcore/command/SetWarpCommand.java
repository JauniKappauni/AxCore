package de.jauni.axcore.command;

import de.jauni.axcore.manager.DatabaseManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SetWarpCommand implements CommandExecutor {
    DatabaseManager databaseManager;

    public SetWarpCommand(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
        }
        Player player = (Player) sender;
        Location loc = Bukkit.getServer().getPlayer(player.getUniqueId()).getLocation();
        try(Connection conn = databaseManager.getConnection()){
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO warps (uuid, world, x, y, z, pitch, yaw, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, uuid.toString());
            ps.setString(2, loc.getWorld().getName());
            ps.setDouble(3, loc.x());
            ps.setDouble(4, loc.y());
            ps.setDouble(5, loc.z());
            ps.setDouble(6, loc.getPitch());
            ps.setDouble(7, loc.getYaw());
            ps.setString(8, args[0]);
            ps.executeUpdate();
            player.sendMessage("Dein Warp" + " " + args[0] + " " + "wurde erfolgreich gesetzt!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

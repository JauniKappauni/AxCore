package de.jauni.axcore.command;

import de.jauni.axcore.manager.DatabaseManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HomeCommand implements CommandExecutor{
    DatabaseManager databaseManager;

    public HomeCommand(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
        }
        Player player = (Player) sender;
        try(Connection conn = databaseManager.getConnection()){
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM homes WHERE uuid = ? AND name = ?");
            ps.setString(1, uuid.toString());
            ps.setString(2, args[0]);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                World world = Bukkit.getWorld(rs.getString("world"));
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                float yaw = rs.getFloat("yaw");
                float pitch = rs.getFloat("pitch");
                Location loc = new Location(world, x, y, z, yaw, pitch);
                String name = rs.getString("name");
                player.teleport(loc);
                player.sendMessage("Du wurdest zu deinem Home" + " " + name + " " + "teleportiert!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

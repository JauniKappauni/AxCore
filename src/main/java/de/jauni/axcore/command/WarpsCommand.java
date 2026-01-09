package de.jauni.axcore.command;

import de.jauni.axcore.manager.DatabaseManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class WarpsCommand implements CommandExecutor {
    DatabaseManager databaseManager;

    public WarpsCommand(DatabaseManager databaseManager){
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM warps");
            ResultSet rs = ps.executeQuery();

            player.sendMessage("Warps:" + " ");
            while(rs.next()){
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int z = rs.getInt("z");
                String name = rs.getString("name");
                player.sendMessage("-" + " " + name + " " + "(" + x + " " + "|" + " " + y + " " + "|" + " " + z + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

package de.jauni.axcore.command;

import de.jauni.axcore.manager.DatabaseManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class BanCommand implements CommandExecutor {
    DatabaseManager databaseManager;

    public BanCommand(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
        Player sourcePlayer = (Player) sender;
        OfflinePlayer targetPlayer = Bukkit.getServer().getOfflinePlayer(args[0]);
        String reason = args[1];
        try(Connection conn = databaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE players SET isBanned = true, reason = ? WHERE uuid = ?");
            ps.setString(1, reason);
            ps.setString(2, targetPlayer.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        targetPlayer.getPlayer().kick();
        sourcePlayer.sendMessage("Du hast" + " " + targetPlayer.getName() + " " + "gebannt.");
        return true;
    }
}

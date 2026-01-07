package de.jauni.axcore.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player1 = (Player) sender;
        Player player2 = Bukkit.getServer().getPlayer(args[0]);
        if(player2 == player1){
            player1.sendMessage("Du kannst dir nicht selbst schreiben!");
            return false;
        }
        String msg = args[1];
        player1.sendMessage("An " + player2.getName() + ": " + ChatColor.DARK_GRAY + msg);
        player2.sendMessage("Von " + player1.getName() + ": " + ChatColor.DARK_GRAY + msg);
        return true;
    }
}

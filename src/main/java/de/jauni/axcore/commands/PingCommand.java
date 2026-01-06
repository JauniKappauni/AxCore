package de.jauni.axcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        int playerPing = player.getPing();
        if(playerPing < 50){
            player.sendMessage(ChatColor.GREEN + "" + playerPing);
        }
        else if (playerPing < 100) {
            player.sendMessage(ChatColor.YELLOW + "" + playerPing);
        }
        else{
            player.sendMessage(ChatColor.RED + "" + playerPing);
        }
        return true;
    }
}

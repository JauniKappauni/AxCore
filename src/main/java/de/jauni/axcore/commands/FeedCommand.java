package de.jauni.axcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        if(player.getFoodLevel() == 20){
            player.sendMessage(ChatColor.RED + "Du bist bereits gesättigt!");
        }
        else{
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.GREEN + "Du wurdest gesättigt");
        }
        return true;
    }
}

package de.jauni.axCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        if(player.getFoodLevel() == 20){
            player.sendMessage("Du bist bereits gesättigt!");
        }
        else{
            player.setFoodLevel(20);
            player.sendMessage("Du wurdest gesättigt");
        }
        return true;
    }
}

package de.jauni.axCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        double playerHealth = player.getHealth();
        if(playerHealth==20.0){
            player.sendMessage("Du bist bereits geheilt!");
        }
        else{
            player.setHealth(20.0);
            player.sendMessage("Du wurdest geheilt");
        }
        return true;
    }
}

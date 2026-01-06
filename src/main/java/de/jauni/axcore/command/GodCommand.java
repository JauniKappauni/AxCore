package de.jauni.axcore.command;

import de.jauni.axcore.AxCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
    AxCore reference;
    public GodCommand(AxCore main){
        this.reference = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        if(!reference.isGod(player)){
            reference.setGod(player,true);
            player.sendMessage(ChatColor.GREEN + "God Mode aktiviert!");
        }
        else{
            reference.setGod(player,false);
            player.sendMessage(ChatColor.RED + "God Mode deaktiviert!");
        }
        return true;
    }
}

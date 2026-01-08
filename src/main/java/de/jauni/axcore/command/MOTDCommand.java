package de.jauni.axcore.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        String newMOTD = "";
        for(int i = 0; i < args.length; i++){
            newMOTD += args[i];
            if(i < args.length - 1){
                newMOTD += " ";
            }
        }
        Bukkit.getServer().motd(Component.text(newMOTD));
        player.sendMessage("Die MOTD wurde auf" + " " + newMOTD + " " + "geändert.");
        return true;
    }
}

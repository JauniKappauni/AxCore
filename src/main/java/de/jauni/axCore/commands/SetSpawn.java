package de.jauni.axCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        Location playerLoc = player.getLocation();
        player.getWorld().setSpawnLocation(playerLoc);
        player.sendMessage(ChatColor.GREEN + "Der Spawn wurde auf" + " " + playerLoc + " " + "gesetzt.");
        return true;
    }
}

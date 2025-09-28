package de.jauni.axCore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Bitte gib einen Spielernamen an.");
            return false;
        }
        Player player = (Player) sender;
        Player player2 = Bukkit.getPlayer(args[0]);
        player2.teleport(player.getLocation());
        player2.sendMessage(ChatColor.GREEN + "Du hast dich zu " + player.getName() + " teleportiert!");
        player.sendMessage(ChatColor.GREEN + "" + player2.getName() + " hat sich zu dir teleportiert!");
        return true;
    }
}

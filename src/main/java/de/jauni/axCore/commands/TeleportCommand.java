package de.jauni.axCore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Bitte gib einen Spielernamen an.");
            return false;
        }
        Player player = (Player) sender;
        Player player2 = Bukkit.getPlayer(args[0]);
        player.teleport(player2.getLocation());
        player.sendMessage(ChatColor.GREEN + "Du hast dich zu " + player2.getName() + " teleportiert!");
        player2.sendMessage(ChatColor.GREEN + "" + player.getName() + " hat sich zu dir teleportiert!");
        return true;
    }

}

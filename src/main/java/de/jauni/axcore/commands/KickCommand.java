package de.jauni.axcore.commands;

import de.jauni.axcore.AxCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
    AxCore reference;
    public KickCommand(AxCore main){
        this.reference = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "Bitte gib einen Spielernamen an.");
            return false;
        }
        Player player = Bukkit.getServer().getPlayer(args[0]);
        reference.getKickedPlayers().add(player.getUniqueId());
        player.kick();
        Bukkit.broadcastMessage(sender.getName() + " kicked " + player.getName());
        return true;
    }
}

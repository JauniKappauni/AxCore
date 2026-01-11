package de.jauni.axcore.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
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

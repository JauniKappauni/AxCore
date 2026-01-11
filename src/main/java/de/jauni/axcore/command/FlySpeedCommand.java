package de.jauni.axcore.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Bitte gib eine Geschwindigkeit an.");
            return false;
        }
        try {
            int speed = Integer.parseInt(args[0]);

            if (speed < 1 || speed > 10) {
                player.sendMessage(ChatColor.RED + "Bitte gib eine Zahl zwischen 1 und 10 an.");
                return false;
            }
            float flySpeed = speed / 10.0f;
            player.setFlySpeed(flySpeed);
            player.sendMessage(ChatColor.GREEN + "Deine Flug Geschwindigkeit wurde auf" + " " + flySpeed + " " + "gesetzt.");
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Bitte gib eine gültige Zahl an.");
            return true;
        }
    return true;
    }
}

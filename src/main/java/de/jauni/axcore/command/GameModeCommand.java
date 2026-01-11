package de.jauni.axcore.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            player.sendMessage(ChatColor.RED + "Bitte gib einen Spielmodus an.\nMögliche Optionen: survival, creative, adventure, spectator");
            return false;
        }
        String mode = args[0].toLowerCase();
        switch (mode) {
            case "survival", "0" -> player.setGameMode(GameMode.SURVIVAL);
            case "creative", "1" -> player.setGameMode(GameMode.CREATIVE);
            case "adventure", "2" -> player.setGameMode(GameMode.ADVENTURE);
            case "spectator", "3" -> player.setGameMode(GameMode.SPECTATOR);
            default -> {
                player.sendMessage(ChatColor.RED + "Ungültiger Spielmodus.\nMögliche Optionen: survival, creative, adventure, spectator");
                return false;
            }
        }
        player.sendMessage(ChatColor.GREEN + "Spielmodus geändert zu " + mode);
        return  true;
    }
}
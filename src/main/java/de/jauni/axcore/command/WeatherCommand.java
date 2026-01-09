package de.jauni.axcore.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        if(args.length == 0){
            player.sendMessage("Verwendung: /weather <sun|rain|thunder>");
            return true;
        }

        String weather = args[0].toLowerCase();
        switch (weather){
            case "sun":
                world.setStorm(false);
                world.setThundering(false);
                world.setWeatherDuration(12000);
                player.sendMessage("Du hast das Wetter auf sonnig gestellt.");
                break;
            case "rain":
                world.setStorm(true);
                world.setThundering(false);
                world.setWeatherDuration(12000);
                player.sendMessage("Du hast das Wetter auf regnerisch gestellt.");
                break;
            case "thunder":
                world.setStorm(true);
                world.setThundering(true);
                world.setWeatherDuration(12000);
                player.sendMessage("Du hast das Wetter auf stürmisch gestellt.");
                break;
            default:
                player.sendMessage("Unbekanntes Wetter! Verwendung: /weather <sun|rain|thunder>.");
                break;
        }
        return true;
    }
}

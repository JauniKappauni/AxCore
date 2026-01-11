package de.jauni.axcore.command.economy;

import de.jauni.axcore.AxCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {

    AxCore reference;

    public MoneyCommand(AxCore reference){
        this.reference = reference;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
        if(args.length == 0){
            Player sourcePlayer = (Player) sender;
            Double balance = reference.getEconomyManager().getBalance(sourcePlayer.getUniqueId());
            sender.sendMessage("Deine Bilanz beträgt" + " " + balance);
            return true;
        }

        String subCommand = args[0].toLowerCase();
        Player sourcePlayer = (Player) sender;
        Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
        Double amount = Double.parseDouble(args[2]);
        Double balance = reference.getEconomyManager().getBalance(sourcePlayer.getUniqueId());


        switch (subCommand){
            case "set":
                reference.getEconomyManager().setBalance(targetPlayer.getUniqueId(), amount);
                sourcePlayer.sendMessage("Du hast die Bilanz von" + " " + targetPlayer.getName() + " " + "auf" + " " + amount + " " + "gesetzt.");
                targetPlayer.sendMessage("Deine Bilanz wurde auf" + " " + amount + " " + "gesetzt.");
                break;
            case "add":
                reference.getEconomyManager().addBalance(targetPlayer.getUniqueId(), amount);
                sourcePlayer.sendMessage("Du hast die Bilanz von" + " " + targetPlayer.getName() + " " + "um" + " " + amount + " " + "erhöht.");
                targetPlayer.sendMessage("Deine Bilanz wurde um" + " " + amount + " " + "erhöht." );
                break;
            case "remove":
                reference.getEconomyManager().removeBalance(targetPlayer.getUniqueId(), amount);
                sourcePlayer.sendMessage("Du hast die Bilanz von" + " " + targetPlayer.getName() + " " + "um" + " " + amount + " " + "reduziert.");
                targetPlayer.sendMessage("Deine Bilanz wurde um" + " " + amount + " " + "reduziert.");
                break;
            case "pay":
                reference.getEconomyManager().removeBalance(sourcePlayer.getUniqueId(), amount);
                reference.getEconomyManager().addBalance(targetPlayer.getUniqueId(), amount);
                sourcePlayer.sendMessage("Du hast" + " " + amount + " " + "an" + " " + targetPlayer.getName() + " " + "gezahlt.");
                targetPlayer.sendMessage("Du hast" + " " + amount + " " + "von" + " " + sourcePlayer.getName() + " " + "bekommen.");
                return true;
            default:
                sourcePlayer.sendMessage("Deine Bilanz beträgt:" + balance);
        }
        return true;
    }
}

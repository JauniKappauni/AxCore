package de.jauni.axcore;

import de.jauni.axcore.listener.DamageListener;
import de.jauni.axcore.listener.PlayerJoinListener;
import de.jauni.axcore.listener.PlayerQuitListener;
import de.jauni.axcore.manager.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AxCore extends JavaPlugin {


    Set<UUID> kickedPlayers = new HashSet<>();
    Set<UUID> godPlayers = new HashSet<>();
    DatabaseManager databaseManager;

    public DatabaseManager getDatabaseManager(){
        return databaseManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        try{
            databaseManager = new DatabaseManager(this);
            if (databaseManager.initDatabaseTables4() == false) {
                getLogger().severe("Error creating users database table!");
                Bukkit.getServer().shutdown();
            }
            getLogger().info("\n\n\n\n\nDatabase connection successfully established!\n\n\n\n\n");
        } catch (Exception e){
            getLogger().severe("Database connection not established!");
            Bukkit.getServer().shutdown();
        }
        getCommand("heal").setExecutor(new de.jauni.axcore.command.HealCommand());
        getCommand("feed").setExecutor(new de.jauni.axcore.command.FeedCommand());
        getCommand("fly").setExecutor(new de.jauni.axcore.command.FlyCommand());
        getCommand("gm").setExecutor(new de.jauni.axcore.command.GameModeCommand());
        getCommand("ping").setExecutor(new de.jauni.axcore.command.PingCommand());
        getCommand("kick").setExecutor(new de.jauni.axcore.command.KickCommand(this));
        getCommand("god").setExecutor(new de.jauni.axcore.command.GodCommand(this));
        getCommand("motd").setExecutor(new de.jauni.axcore.command.MOTDCommand());
        getCommand("day").setExecutor(new de.jauni.axcore.command.DayCommand());
        getCommand("night").setExecutor(new de.jauni.axcore.command.NightCommand());
        getCommand("weather").setExecutor(new de.jauni.axcore.command.WeatherCommand());
        getCommand("flyspeed").setExecutor(new de.jauni.axcore.command.FlySpeedCommand());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Set<UUID> getKickedPlayers(){
        return kickedPlayers;
    }

    public boolean isGod(Player player){
        return godPlayers.contains(player.getUniqueId());
    }

    public void setGod(Player player, boolean state){
        if(state){
            godPlayers.add(player.getUniqueId());
        } else {
            godPlayers.remove(player.getUniqueId());
        }
    }
}
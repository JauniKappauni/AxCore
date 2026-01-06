package de.jauni.axcore;

import de.jauni.axcore.listener.PlayerJoinListener;
import de.jauni.axcore.listener.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AxCore extends JavaPlugin {

    Set<UUID> kickedPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getCommand("heal").setExecutor(new de.jauni.axcore.command.HealCommand());
        getCommand("feed").setExecutor(new de.jauni.axcore.command.FeedCommand());
        getCommand("fly").setExecutor(new de.jauni.axcore.command.FlyCommand());
        getCommand("gm").setExecutor(new de.jauni.axcore.command.GameModeCommand());
        getCommand("ping").setExecutor(new de.jauni.axcore.command.PingCommand());
        getCommand("tp").setExecutor(new de.jauni.axcore.command.TeleportCommand());
        getCommand("tphere").setExecutor(new de.jauni.axcore.command.TeleportHereCommand());
        getCommand("kick").setExecutor(new de.jauni.axcore.command.KickCommand(this));
        getCommand("spawn").setExecutor(new de.jauni.axcore.command.SpawnCommand());
        getCommand("setspawn").setExecutor(new de.jauni.axcore.command.SetSpawn());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Set<UUID> getKickedPlayers(){
        return kickedPlayers;
    }

    public boolean isSpawnOnJoin(){
        return getConfig().getBoolean("spawnOnJoin", true);
    }
}

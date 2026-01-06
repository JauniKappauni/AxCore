package de.jauni.axCore;

import de.jauni.axCore.listeners.PlayerJoinListener;
import de.jauni.axCore.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AxCore extends JavaPlugin {

    Set<UUID> kickedPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("heal").setExecutor(new de.jauni.axCore.commands.HealCommand());
        getCommand("feed").setExecutor(new de.jauni.axCore.commands.FeedCommand());
        getCommand("fly").setExecutor(new de.jauni.axCore.commands.FlyCommand());
        getCommand("gm").setExecutor(new de.jauni.axCore.commands.GameModeCommand());
        getCommand("ping").setExecutor(new de.jauni.axCore.commands.PingCommand());
        getCommand("tp").setExecutor(new de.jauni.axCore.commands.TeleportCommand());
        getCommand("tphere").setExecutor(new de.jauni.axCore.commands.TeleportHereCommand());
        getCommand("kick").setExecutor(new de.jauni.axCore.commands.KickCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Set<UUID> getKickedPlayers(){
        return kickedPlayers;
    }
}

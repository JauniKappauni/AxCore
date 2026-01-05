package de.jauni.axCore;

import de.jauni.axCore.listeners.PlayerJoinListener;
import de.jauni.axCore.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AxCore extends JavaPlugin {

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
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

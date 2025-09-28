package de.jauni.axCore;

import org.bukkit.plugin.java.JavaPlugin;

public final class AxCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("heal").setExecutor(new de.jauni.axCore.commands.HealCommand());
        getCommand("feed").setExecutor(new de.jauni.axCore.commands.FeedCommand());
        getCommand("fly").setExecutor(new de.jauni.axCore.commands.FlyCommand());
        getCommand("gm").setExecutor(new de.jauni.axCore.commands.GameModeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

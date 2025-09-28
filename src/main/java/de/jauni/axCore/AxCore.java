package de.jauni.axCore;

import org.bukkit.plugin.java.JavaPlugin;

public final class AxCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("heal").setExecutor(new de.jauni.axCore.commands.HealCommand());
        getCommand("feed").setExecutor(new de.jauni.axCore.commands.FeedCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

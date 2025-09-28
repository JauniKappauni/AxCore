package de.jauni.axCore;

import org.bukkit.plugin.java.JavaPlugin;

public final class AxCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("heal").setExecutor(new de.jauni.axCore.commands.HealCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

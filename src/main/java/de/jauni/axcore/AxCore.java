package de.jauni.axcore;

import de.jauni.axcore.listener.DamageListener;
import de.jauni.axcore.listener.PlayerJoinListener;
import de.jauni.axcore.listener.PlayerQuitListener;
import de.jauni.axcore.manager.DatabaseManager;
import de.jauni.axcore.manager.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AxCore extends JavaPlugin {

    Set<UUID> kickedPlayers = new HashSet<>();
    Set<UUID> godPlayers = new HashSet<>();
    EconomyManager economyManager;
    public EconomyManager getEconomyManager(){
        return economyManager;
    }
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
            if(databaseManager.initDatabaseTables() == false){
                getLogger().severe("Error creating balance database table!");
                Bukkit.getServer().shutdown();
            }
            if(databaseManager.initDatabaseTables2() == false){
                getLogger().severe("Error creating homes database table!");
                Bukkit.getServer().shutdown();
            }
            if(databaseManager.initDatabaseTables3() == false){
                getLogger().severe("Error creating warps database table!");
                Bukkit.getServer().shutdown();
            }
            if (databaseManager.initDatabaseTables4() == false) {
                getLogger().severe("Error creating users database table!");
                Bukkit.getServer().shutdown();
            }
            economyManager = new EconomyManager(databaseManager);
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
        getCommand("tp").setExecutor(new de.jauni.axcore.command.TeleportCommand());
        getCommand("tphere").setExecutor(new de.jauni.axcore.command.TeleportHereCommand());
        getCommand("kick").setExecutor(new de.jauni.axcore.command.KickCommand(this));
        getCommand("spawn").setExecutor(new de.jauni.axcore.command.SpawnCommand());
        getCommand("setspawn").setExecutor(new de.jauni.axcore.command.SetSpawnCommand());
        getCommand("god").setExecutor(new de.jauni.axcore.command.GodCommand(this));
        getCommand("msg").setExecutor(new de.jauni.axcore.command.MessageCommand());
        getCommand("money").setExecutor(new de.jauni.axcore.command.economy.MoneyCommand(this));
        getCommand("motd").setExecutor(new de.jauni.axcore.command.MOTDCommand());
        getCommand("home").setExecutor(new de.jauni.axcore.command.HomeCommand(databaseManager));
        getCommand("sethome").setExecutor(new de.jauni.axcore.command.SetHomeCommand(databaseManager));
        getCommand("day").setExecutor(new de.jauni.axcore.command.DayCommand());
        getCommand("night").setExecutor(new de.jauni.axcore.command.NightCommand());
        getCommand("warp").setExecutor(new de.jauni.axcore.command.WarpCommand(databaseManager));
        getCommand("setwarp").setExecutor(new de.jauni.axcore.command.SetWarpCommand(databaseManager));
        getCommand("homes").setExecutor(new de.jauni.axcore.command.HomesCommand(databaseManager));
        getCommand("warps").setExecutor(new de.jauni.axcore.command.WarpsCommand(databaseManager));
        getCommand("weather").setExecutor(new de.jauni.axcore.command.WeatherCommand());
        getCommand("ban").setExecutor(new de.jauni.axcore.command.BanCommand(databaseManager));
        getCommand("unban").setExecutor(new de.jauni.axcore.command.UnbanCommand(databaseManager));
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

    public boolean isSpawnOnJoin(){
        return getConfig().getBoolean("spawnOnJoin", true);
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
    public boolean isBanned(UUID uuid) {
        try (Connection conn = databaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT isBanned FROM players WHERE uuid = ?");
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }
                return rs.getBoolean("isBanned");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBanReason(UUID uuid) {
        try (Connection conn = databaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT reason FROM players WHERE uuid = ?");
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return "false";
                }
                return rs.getString("reason");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
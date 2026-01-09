package de.jauni.axcore.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    HikariDataSource hikari;

    public DatabaseManager(JavaPlugin plugin){
        FileConfiguration pluginConfig = plugin.getConfig();

        String url = pluginConfig.getString("database.url");
        String username = pluginConfig.getString("database.username");
        String password = pluginConfig.getString("database.password");

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        hikari = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }
    public boolean initDatabaseTables() throws SQLException {
        try(Connection conn = getConnection()){
                try(PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS balances (uuid VARCHAR(255) NOT NULL PRIMARY KEY, balance DOUBLE)")){
                    ps.executeUpdate();
                        return true;
                }
        }
    }

    public boolean initDatabaseTables2() throws SQLException {
        try(Connection conn = getConnection()){
            try(PreparedStatement ps2 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS homes (uuid VARCHAR(255), world VARCHAR(255), x DOUBLE, y DOUBLE, z DOUBLE, pitch FLOAT, yaw FLOAT, name VARCHAR(255) PRIMARY KEY)")){
                ps2.executeUpdate();
                return true;
            }
        }
    }
}
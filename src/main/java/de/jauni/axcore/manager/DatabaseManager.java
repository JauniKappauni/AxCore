package de.jauni.axcore.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    HikariDataSource hikari;

    public DatabaseManager(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/plugin");
        config.setUsername("root");
        config.setPassword("");
        hikari = new HikariDataSource(config);
    }

    Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }
}

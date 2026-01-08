package de.jauni.axcore.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
    public boolean initDatabaseTables() {
        try(Connection conn = getConnection()){
                try(PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS balances (uuid VARCHAR(255) NOT NULL PRIMARY KEY, balance DOUBLE)")){
                    ps.executeUpdate();
                    return true;
                }
        }
        catch (SQLException e){
            return false;
        }
    }
}

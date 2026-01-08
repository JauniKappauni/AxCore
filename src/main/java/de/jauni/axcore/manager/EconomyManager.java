package de.jauni.axcore.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyManager {

    DatabaseManager databaseManager;

    public EconomyManager(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    public double getBalance(UUID uuid){
        try(Connection conn = databaseManager.getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT balance FROM balances WHERE uuid = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getDouble("balance");
            }else {
                setBalance(uuid, 0.0);
                return 0.0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBalance(UUID uuid, Double amount){
        try(Connection conn = databaseManager.getConnection()){
            PreparedStatement select = conn.prepareStatement("SELECT balance FROM balances WHERE uuid = ?");
            select.setString(1, uuid.toString());
            ResultSet rs = select.executeQuery();

            if(rs.next()){
                PreparedStatement update = conn.prepareStatement("UPDATE balances SET balance = ? WHERE uuid = ?");
                    update.setDouble(1, amount);
                    update.setString(2, uuid.toString());
                    update.execute();
            } else{
                    PreparedStatement insert = conn.prepareStatement("INSERT INTO balances (uuid, balance) VALUES (?, ?)");
                    insert.setString(1, uuid.toString());
                    insert.setDouble(2, amount);
                    insert.executeUpdate();
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addBalance(UUID uuid, Double amount){
        setBalance(uuid, getBalance(uuid) + amount);
    }

    public void removeBalance(UUID uuid, Double amount){
        double current = getBalance(uuid);

        if(current < amount){
            return;
        }

        setBalance(uuid, current - amount);
    }
}

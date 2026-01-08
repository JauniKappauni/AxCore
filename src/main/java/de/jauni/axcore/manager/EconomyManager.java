package de.jauni.axcore.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyManager {

    Map<UUID, Double> balances = new HashMap<>();

    public double getBalance(UUID uuid){
        return balances.getOrDefault(uuid, 0.0);
    }

    public void setBalance(UUID uuid, Double amount){
        balances.put(uuid, amount);
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
        return;
    }
}

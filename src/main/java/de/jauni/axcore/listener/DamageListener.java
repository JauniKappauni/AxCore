package de.jauni.axcore.listener;

import de.jauni.axcore.AxCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
    AxCore reference;
    public DamageListener(AxCore main){
        this.reference = main;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) event.getEntity();
        if(reference.isGod(player)){
            event.setCancelled(true);
        }
    }
}

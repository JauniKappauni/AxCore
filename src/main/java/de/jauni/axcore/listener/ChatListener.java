package de.jauni.axcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChatMessage(PlayerChatEvent event){
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();
        String formattedMessage = playerName + " " + ":" + " " + message;
        event.setFormat(formattedMessage);
    }
}

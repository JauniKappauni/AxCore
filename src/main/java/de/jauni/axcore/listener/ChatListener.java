package de.jauni.axcore.listener;

import de.jauni.axcore.AxCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {
    AxCore reference;
    public ChatListener(AxCore reference){
        this.reference = reference;
    }
    @EventHandler
    public void onPlayerChatMessage(PlayerChatEvent event){
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();

        String formatPlayer = reference.getMessage("chat.prefix");
        String formatSeparator = reference.getMessage("chat.separator");
        String formatMessage = reference.getMessage("chat.suffix");

        String formattedMessage = formatPlayer.replace("player", playerName) + " " + formatSeparator + " " + formatMessage.replace("message", message);

        event.setFormat(formattedMessage);
    }
}

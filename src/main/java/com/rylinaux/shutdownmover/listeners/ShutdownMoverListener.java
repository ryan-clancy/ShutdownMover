package com.rylinaux.shutdownmover.listeners;

import com.rylinaux.shutdownmover.ShutdownMover;

import java.util.List;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Listen for when players are kicked, send to fallback if applicable.
 *
 * @author rylinaux
 */
public class ShutdownMoverListener implements Listener {

    /**
     * The instance of the plugin.
     */
    private final ShutdownMover plugin;

    /**
     * Construct our object.
     *
     * @param plugin the plugin instance
     */
    public ShutdownMoverListener(ShutdownMover plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onKick(ServerKickEvent event) {

        // Convert the Component for the kick reason into a String.
        String reason = TextComponent.toPlainText(event.getKickReasonComponent()).toLowerCase();

        ServerInfo fallback = plugin.getFallback();

        if (fallback != null) {
            List<String> validTerms = plugin.getTerms();
            for (String term : validTerms) {
                if (reason.contains(term)) {
                    event.setCancelled(true);
                    event.setCancelServer(fallback);
                }
            }
        }
    }

}

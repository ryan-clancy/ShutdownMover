package com.rylinaux.shutdownmover.listeners;

import com.rylinaux.shutdownmover.ShutdownMover;

import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ShutdownMoverListener implements Listener {

    private static final String[] TERMS = {"closed", "restart", "restarting", "shutdown"};

    @EventHandler
    public void onKick(ServerKickEvent event) {

        String reason = TextComponent.toPlainText(event.getKickReasonComponent()).toLowerCase();

        ServerInfo fallback = ShutdownMover.getFallback();

        if (fallback != null) {
            List<String> validTerms = Arrays.asList(TERMS);
            for (String term : validTerms) {
                if (reason.contains(term)) {
                    event.setCancelled(true);
                    event.setCancelServer(fallback);
                }
            }
        }
    }

}

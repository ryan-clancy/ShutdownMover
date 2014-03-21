package com.rylinaux.shutdownmover;

import com.rylinaux.shutdownmover.configuration.BungeeConfig;
import com.rylinaux.shutdownmover.listeners.ShutdownMoverListener;

import java.util.List;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Move players to the fallback server on kick if the reason contains a valid term.
 *
 * @author rylinaux
 */
public class ShutdownMover extends Plugin {

    /**
     * The fallback server to be kicked to.
     */
    private ServerInfo fallback;

    /**
     * The valid terms to used to determine if a server is shutting down.
     */
    private List<String> terms;

    @Override
    public void onEnable() {

        BungeeConfig config = new BungeeConfig(this);

        fallback = this.getProxy().getServerInfo(config.getConfig().getString("server"));
        terms = config.getConfig().getStringList("terms");

        this.getProxy().getPluginManager().registerListener(this, new ShutdownMoverListener(this));

    }

    @Override
    public void onDisable() {
        this.getProxy().getPluginManager().unregisterListeners(this);
    }

    /**
     * Returns the fallback server.
     *
     * @return the fallback server.
     */
    public ServerInfo getFallback() {
        return fallback;
    }

    /**
     * Returns the list of valid terms for fallback kicking.
     *
     * @return the list of valid terms for fallback kicking
     */
    public List<String> getTerms() {
        return terms;
    }

}
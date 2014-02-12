package com.rylinaux.shutdownmover;

import com.rylinaux.shutdownmover.configuration.BungeeConfig;
import com.rylinaux.shutdownmover.listeners.ShutdownMoverListener;

import java.util.List;

import lombok.Getter;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class ShutdownMover extends Plugin {

    /**
     * The fallback server for being kicked on restart.
     */
    private ServerInfo fallback;

    /**
     * The valid terms to used to determine if a server is shutting down.
     */
    private List<String> terms;

    /**
     * The configuration for the plugin.
     */
    private BungeeConfig config;

    @Override
    public void onEnable() {

        config = new BungeeConfig(this);

        fallback = this.getProxy().getServerInfo(config.getConfig().getString("server"));
        terms = config.getConfig().getStringList("terms");

        this.getProxy().getPluginManager().registerListener(this, new ShutdownMoverListener(this));

    }

    @Override
    public void onDisable() {
        this.getProxy().getPluginManager().unregisterListeners(this);
    }

}
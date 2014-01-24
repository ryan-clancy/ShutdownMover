package com.rylinaux.shutdownmover;

import com.rylinaux.shutdownmover.listeners.ShutdownMoverListener;

import lombok.Getter;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

public class ShutdownMover extends Plugin {

    @Getter
    private static ShutdownMover instance;

    @Getter
    private static ServerInfo fallback;

    @Override
    public void onEnable() {
        instance = this;
        fallback = this.getProxy().getServerInfo("hub");
        this.getProxy().getPluginManager().registerListener(this, new ShutdownMoverListener());
    }

    @Override
    public void onDisable() {
        this.getProxy().getPluginManager().unregisterListeners(this);
        instance = null;
    }

}

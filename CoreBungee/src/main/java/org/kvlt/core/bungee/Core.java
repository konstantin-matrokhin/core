package org.kvlt.core.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import org.kvlt.core.bungee.packets.DisconnectPacket;

public final class Core extends Plugin {

    private static Plugin instance;
    private static ProxyCore core;

    @Override
    public void onEnable() {
        instance = this;
        core = new MainProxyCore(this);
        core.connect();
        System.out.println("MAIN: " + (getAPI() == null));
    }

    @Override
    public void onDisable() {
        core.getConnectionManager().setDisconnecting(true);
        try {
            DisconnectPacket dp = new DisconnectPacket();
            dp.send();
        } catch (Exception ignored) {

        }
    }

    public static synchronized ProxyCore getAPI() {
        return core;
    }

    public static synchronized Plugin getPlugin() {
        return instance;
    }

}

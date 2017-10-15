package net.enot.dartabungee.client.listeners;

import net.enot.dartabungee.DartaBungee;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Енот on 24.04.2017.
 */
public class PingListener implements Listener {

    public static int online = 0;

    public PingListener(){
        DartaBungee.getInstance().getProxy().getPluginManager().registerListener(DartaBungee.getInstance(), this);
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        ServerPing ping = e.getResponse();
        ping.setPlayers(new ServerPing.Players(online + 1, online, ping.getPlayers().getSample()));
        e.setResponse(ping);
    }

}

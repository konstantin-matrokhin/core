package org.kvlt.core.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.entities.PlayerAdapter;
import org.kvlt.core.bungee.utils.Log;
import org.kvlt.core.entities.CorePlayer;
import org.kvlt.core.packets.ProxyLoginPacket;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer p = event.getPlayer();
        Log.$("proxy login packet");
        CoreBungee.get().getCoreServer().writeAndFlush(
                new ProxyLoginPacket(PlayerAdapter.asCorePlayer(p), CoreBungee.get().getServerName())
        );
    }

}

package org.kvlt.core.bungee;

import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingEventListener implements Listener {

    private final String DEFAULT_MOTD = "&cНет соединения с главным сервером&r";
    private String motd;

    @EventHandler
    public void onPing(ProxyPingEvent event) {
//        ServerPing response = event.getResponse();
//        String newMotd = ChatColor.translateAlternateColorCodes('&',
//                motd.isEmpty() ? DEFAULT_MOTD : motd);
//
//        response.setDescription(newMotd);
//        ServerPing.Players players = response.getPlayers();
//        players.setMax(players.getOnline() + 1);
//        response.setPlayers(players);
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }
}

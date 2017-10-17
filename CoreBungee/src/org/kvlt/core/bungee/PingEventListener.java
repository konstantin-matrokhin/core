package org.kvlt.core.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingEventListener implements Listener {

    private final String DEFAULT_MOTD = "Нет соединения с главным сервером";
    private String motd = "";

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing response = event.getResponse();
        String newMotd = ChatColor.translateAlternateColorCodes('&',
                motd.isEmpty() ? DEFAULT_MOTD : motd);
        response.setDescription(newMotd);
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }
}

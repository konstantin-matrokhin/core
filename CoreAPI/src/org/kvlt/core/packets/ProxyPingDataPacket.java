package org.kvlt.core.packets;

import org.kvlt.core.bungee.CoreBungee;

public class ProxyPingDataPacket extends Packet {

    private String motd;

    public ProxyPingDataPacket(String motd) {
        this.motd = motd;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
        CoreBungee.get().getPingEventListener().setMotd(getMotd());
    }

    public String getMotd() {
        return motd;
    }

}

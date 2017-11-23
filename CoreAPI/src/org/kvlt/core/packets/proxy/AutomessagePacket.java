package org.kvlt.core.packets.proxy;

import net.md_5.bungee.BungeeCord;
import org.kvlt.core.packets.Packet;

public class AutomessagePacket extends Packet {

    private String message;

    public AutomessagePacket(String message) {
        this.message = message;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
        BungeeCord.getInstance().broadcast(message);
    }
}

package org.kvlt.core.packets.proxy;

import net.md_5.bungee.BungeeCord;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class ProxySwitchServerPacket extends Packet {

    private OnlinePlayer op;
    private String server;

    public ProxySwitchServerPacket(OnlinePlayer op, String server) {
        this.op = op;
        this.server = server;
    }

    @Override
    protected void onCore() {
        OnlinePlayer p = CoreServer.get().getOnlinePlayers().get(op.getName());
        p.getCurrentProxy().send(this);
        Log.$("sending " + p.getName() + " to " + server);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
        BungeeCord.getInstance().getPlayer(op.getName()).connect(BungeeCord.getInstance().getServerInfo(server));
    }
}

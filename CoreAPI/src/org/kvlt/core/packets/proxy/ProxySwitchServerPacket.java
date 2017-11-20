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

    public ProxySwitchServerPacket(OnlinePlayer op) {
        this.op = op;
    }

    private String parentServer() {
        OnlinePlayer p = CoreServer.get().getOnlinePlayers().get(op.getName());

        String currentServer = p.getCurrentServer().getName();
        String lobbyType = currentServer.substring(0, 2);
        String end = currentServer.split("-")[1];

        if (currentServer.contains("lobby")) {
            return hub(end);
        } else {
            return lobby(lobbyType, end);
        }
    }

    private String lobby(String prefix, String numStr) {
        String lobbyName = prefix + "lobby-" + numStr;
        GameServer target = CoreServer.get().getGameServers().getNode(lobbyName);
        return target == null ? null : lobbyName;
    }

    private String hub(String numStr) {
        String hubName = "hub-" + numStr;
        GameServer hub = CoreServer.get().getGameServers().getNode(hubName);
        return hub == null ? server : hubName;
    }

    @Override
    protected void onCore() {
        OnlinePlayer p = CoreServer.get().getOnlinePlayers().get(op.getName());
        if (server == null) {
            server = parentServer();
        }
        p.getCurrentProxy().send(this);
        Log.$("sending " + p.getName() + "(" + p.getCurrentProxy().getName() +")" + " to " + server);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
        System.out.println(op.getName() + " joining server: " + server);
        BungeeCord.getInstance().getPlayer(op.getName()).connect(BungeeCord.getInstance().getServerInfo(server));
    }
}

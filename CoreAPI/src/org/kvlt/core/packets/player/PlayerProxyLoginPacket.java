package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.packets.Packet;

public class PlayerProxyLoginPacket extends Packet {

    private OnlinePlayer player;
    private String proxy;

    public PlayerProxyLoginPacket(OnlinePlayer player, String proxy) {
        this.player = player;
        this.proxy = proxy;
    }

    @Override
    protected void onCore() {
        Proxy cproxy = CoreServer.get().getProxies().getNode(proxy);
        boolean containsSameIp = CoreServer.get()
                .getOnlinePlayers()
                .stream()
                .anyMatch(p -> p.getIp().equalsIgnoreCase(player.getIp()));

        if (containsSameIp) {
            PlayerKickPacket pkp = new PlayerKickPacket(player.getName());
            cproxy.send(pkp);
            return;
        }
        cproxy.getPlayers().add(player);
        PlayerDB.loadOnlinePlayer(player);
        CoreServer.get().getOnlinePlayers().add(player);
        player.setCurrentProxy(CoreServer.get().getProxies().getNode(proxy));

    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
    }

}

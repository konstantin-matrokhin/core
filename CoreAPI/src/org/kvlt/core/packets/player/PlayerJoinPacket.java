package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

import java.io.Serializable;

@Deprecated
public class PlayerJoinPacket extends Packet implements Serializable {

    private OnlinePlayer player;
    private String serverName;

    public PlayerJoinPacket(OnlinePlayer p, String serverName) {
        this.player = p;
    }

    @Override
    public void onCore() {
        player.setCurrentServer(CoreServer.get().getGameServers().getNode(serverName));
        PlayerDB.loadOnlinePlayer(player);
        CoreServer.get().getOnlinePlayers().add(player);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}

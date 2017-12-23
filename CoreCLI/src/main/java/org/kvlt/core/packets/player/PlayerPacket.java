package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketIn;

public abstract class PlayerPacket implements PacketIn {

    private String playerName;
    private ServerPlayer player;

    protected boolean ensurePlayer() {
        player = CoreServer.get().getOnlinePlayers().get(playerName);
        return player != null;
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}

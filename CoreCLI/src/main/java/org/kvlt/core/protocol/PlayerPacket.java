package org.kvlt.core.protocol;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;

public abstract class PlayerPacket implements PacketIn {

    private String playerName;
    private OnlinePlayer player;

    protected boolean ensurePlayer() {
        player = CoreServer.get().getOnlinePlayers().get(playerName);
        return player != null;
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}

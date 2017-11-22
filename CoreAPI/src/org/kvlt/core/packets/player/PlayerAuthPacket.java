package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class PlayerAuthPacket extends Packet {

    private String playerName;
    private String password;
    private boolean successful;

    public PlayerAuthPacket(String playerName, String password) {
        this.playerName = playerName;
    }

    @Override
    protected void onCore() {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);
        boolean correctPassword = PlayerDB.correctPassword(op, password);
        Log.$(correctPassword + "");
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}

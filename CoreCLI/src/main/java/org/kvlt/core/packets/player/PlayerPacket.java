package org.kvlt.core.packets.player;

import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.utils.Localization;

public abstract class PlayerPacket implements PacketIn {

    private String playerName;
    private ServerPlayer player;
    private String response;

    protected boolean ensurePlayer() {
        player = Core.get().getOnlinePlayers().get(playerName);
        return player != null;
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    protected void writeMsg(String response) {
        this.response = Localization.get(getPlayer(), response);
    }

    protected void writeMsg(String response, Object... format) {
        writeMsg(String.format(response, format));
    }

    protected void sendMsg(Channel channel) {
        new MessagePacket(getPlayerName(), response).send(channel);
    }

}

package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.PlayerPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerChatPacket extends PlayerPacket {

    private String message;
    private boolean isCommand;

    public PlayerChatPacket(String playerName, String message, boolean isCommand) {
        setPlayerName(playerName);
        this.message = message;
        this.isCommand = isCommand;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(getPlayerName(), out);
        PacketUtil.writeString(message, out);
        out.writeBoolean(isCommand);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_CHAT_PACKET;
    }
}

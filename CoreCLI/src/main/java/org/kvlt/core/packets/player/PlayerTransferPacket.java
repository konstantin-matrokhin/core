package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerTransferPacket extends CorePacketOut {

    private String player;
    private String destinationServer;

    public PlayerTransferPacket(String player, String destinationServer) {
        this.player = player;
        this.destinationServer = destinationServer;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(destinationServer, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_TRANSFER_PACKET;
    }
}

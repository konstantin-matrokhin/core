package org.kvlt.core.bukkit.commands;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bukkit.packets.BukkitPacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class TransferRequestPacket extends BukkitPacketOut {

    private String player;
    private String destination;

    public TransferRequestPacket(String player, String destination) {
        this.player = player;
        this.destination = destination;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(destination, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_TRANSFER_PACKET;
    }

}

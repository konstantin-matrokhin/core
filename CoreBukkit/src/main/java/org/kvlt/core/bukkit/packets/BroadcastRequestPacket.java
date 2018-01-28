package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BroadcastRequestPacket extends BukkitPacketOut {

    private String sender;
    private String message;
    private String server;

    public BroadcastRequestPacket(String sender, String message, String server) {
        this.sender = sender;
        this.message = message;
        this.server = server;
    }

    public BroadcastRequestPacket(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(sender, out);
        PacketUtil.writeString(message, out);

        if (server == null || server.isEmpty()) {
            server = "none";
        }
        PacketUtil.writeString(server, out);
    }

    @Override
    public int getId() {
        return Packets.BROADCAST_PACKET;
    }

}

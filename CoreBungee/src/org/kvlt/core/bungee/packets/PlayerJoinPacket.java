package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.protocol.PacketUtil;

public class PlayerJoinPacket extends BungeeOutPacket {

    private String playerName;
    private String ip;
    private String uuid;

    public PlayerJoinPacket(String playerName, String ip, String uuid) {
        this.playerName = playerName;
        this.ip = ip;
        this.uuid = uuid;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
        PacketUtil.writeString(CoreBungee.get().getServerName(), out);
        PacketUtil.writeString(ip, out);
        PacketUtil.writeString(uuid, out);
    }

    @Override
    public int getId() {
        return 6;
    }

}

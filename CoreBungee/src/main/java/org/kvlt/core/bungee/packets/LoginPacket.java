package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.protocol.PlayerPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class LoginPacket extends PlayerPacket {

    private String ip;
    private String uuid;

    public LoginPacket(String playerName, String ip, String uuid) {
        setPlayerName(playerName);
        this.ip = ip;
        this.uuid = uuid;

        System.out.println(ip + " | " + uuid);
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(getPlayerName(), out);
        PacketUtil.writeString(CoreBungee.get().getServerName(), out);
        PacketUtil.writeString(ip, out);
        PacketUtil.writeString(uuid, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_LOGIN_PACKET;
    }
}

package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class HubRequestPacket extends PlayerPacket {

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
    }

    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {
            String proxyName = getPlayer().getCurrentProxy().getName();
            String playerName = getPlayer().getName();

            new PlayerTransferPacket(playerName, calculateRootServer(proxyName)).send(Destination.BUNGEE, proxyName);
        }
    }

    private String calculateRootServer(String proxy) {
        String lobbyType = proxy.substring(0, 2);
        //String serverNum = proxy.substring()
        return "";
    }

    @Override
    public int getId() {
        return Packets.HUB_PACKET;
    }

}

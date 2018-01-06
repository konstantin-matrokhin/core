package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class TransferRequestPacket extends PlayerPacket {

    private String server;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        server = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        ServerPlayer player = getPlayer();
        String name = player.getName();
        String proxy = player.getCurrentProxy().getName();
        GameServer destination = Core.get().getGameServers().getNode(server);

        if (destination != null) {
            new PlayerTransferPacket(name, server).send(Destination.BUNGEE, proxy);
            System.out.println("transfering..");
        } else {
            System.out.println("dest is null(");
        }
    }

    @Override
    public int getId() {
        return Packets.PLAYER_TRANSFER_PACKET;
    }

}

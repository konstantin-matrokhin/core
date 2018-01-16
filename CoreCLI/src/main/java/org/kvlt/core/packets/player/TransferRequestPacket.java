package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerSwitchServerEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

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
        GameServer destination = Core.get().getGameServers().getNode(server);

        PlayerSwitchServerEvent switchEvent = new PlayerSwitchServerEvent(player, destination);
        switchEvent.invoke();
        if (switchEvent.isCancelled()) {
            Log.$(switchEvent.getName() + " отменено.");
            return;
        }

        String name = player.getName();
        String proxy = player.getCurrentProxy().getName();

        if (destination != null) {
            new PlayerTransferPacket(name, server).send(Destination.BUNGEE, proxy);
            System.out.println("переносим..");
        } else {
            System.out.println("не удалось переместить");
        }
    }

    @Override
    public int getId() {
        return Packets.PLAYER_TRANSFER_PACKET;
    }

}

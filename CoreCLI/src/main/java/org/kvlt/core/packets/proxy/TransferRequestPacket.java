package org.kvlt.core.packets.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerSwitchServerEvent;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.player.PlayerPacket;
import org.kvlt.core.packets.player.PlayerTransferPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Finder;
import org.kvlt.core.utils.Log;

import java.util.Random;

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
        GameServers destinations = Finder.getGameServers(server);
        destinations.stream()//TODO FIX NOW

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

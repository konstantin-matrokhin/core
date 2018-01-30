package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.network.PlayerSwitchServerEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Finder;
import org.kvlt.core.utils.Log;

import java.util.Random;

public class TransferRequestPacket extends PlayerPacket {

    private static Random rnd = new Random();
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
        int randomIndex = rnd.nextInt(destinations.list().size());
        GameServer selectedServer = destinations.list().get(randomIndex);

        PlayerSwitchServerEvent switchEvent = new PlayerSwitchServerEvent(player, selectedServer);
        switchEvent.invoke();
        if (switchEvent.isCancelled()) {
            Log.$(switchEvent.getName() + " отменено.");
            return;
        }

        String name = player.getName();
        String proxy = player.getCurrentProxy().getName();

        if (selectedServer != null) {
            new PlayerTransferPacket(name, selectedServer.getName()).send(Destination.BUNGEE, proxy);
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

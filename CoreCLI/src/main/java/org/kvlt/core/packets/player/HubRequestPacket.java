package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;

public class HubRequestPacket extends PlayerPacket {

    private static final Random rnd = new Random();

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
    }

    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {
            String currentServer = getPlayer().getCurrentServer().getName();
            String currentProxy = getPlayer().getCurrentProxy().getName();
            String playerName = getPlayer().getName();

            new PlayerTransferPacket(playerName,
                    calculateRootServer(currentServer)).send(Destination.BUNGEE, currentProxy);
        }
    }

    private String calculateRootServer(String server) {
        String lobbyType = server.substring(0, 2);
        //todo optimize
        Optional<List<GameServer>> optList = Optional.ofNullable(Core.get().getGameServers()
                .stream()
                .filter(s -> s.getName().startsWith(lobbyType + "lobby-"))
                .collect(Collectors.toList()));

        if (optList.isPresent()) {
            List<GameServer> cases = optList.get();
            if (cases.size() > 0) {
                return cases.get(rnd.nextInt(cases.size())).getName();
            }
        }

        return calculateHub();
    }

    private String calculateHub() {
        List<GameServer> hubs = Core.get().getGameServers()
                .stream()
                .filter(h -> h.getName().startsWith("hub-"))
                .collect(Collectors.toList());

        if (hubs != null && hubs.size() > 0) {
            return hubs.get(rnd.nextInt(hubs.size())).getName();
        }

        return "hub-1";
    }

    @Override
    public int getId() {
        return Packets.HUB_PACKET;
    }

}

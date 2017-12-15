package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerPreLoginEvent;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

import java.util.concurrent.TimeUnit;

public class PlayerPreLoginPacket implements PacketIn {

    private String playerName;
    private String proxyName;
    private String ip;
    private String uuid;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        proxyName = PacketUtil.readString(in);
        ip = PacketUtil.readString(in);
        uuid = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer op = new ServerPlayer(playerName);

        op.setIp(ip);
        op.setUuid(uuid);

        PlayerDB.loadPlayer(op);
        Runnable r = () -> {
            try {
                PlayerDB.executor.awaitTermination(5, TimeUnit.SECONDS);
                int id = op.getId();
                IdPacket idPacket = new IdPacket(playerName, id);
                idPacket.send(channel);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        PlayerDB.executor.execute(r);

        CoreServer.get().getUnloggedPlayers().add(op);
        Proxy proxy = CoreServer.get().getProxies().getNode(proxyName);

        System.out.println(String.format("Игрок %s подключился к прокси-серверу %s",
                playerName,
                proxyName));

        PlayerPreLoginEvent prle = new PlayerPreLoginEvent(op, proxy);
        prle.invoke();
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PRELOGIN_PACKET;
    }
}

package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerLoginEvent;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class PlayerLoginPacket implements PacketIn {

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
        PlayerDB.loadPlayer(op);
        CoreServer.get().getUnloggedPlayers().add(op);

        Proxy proxy = CoreServer.get().getProxies().getNode(proxyName);

        System.out.println(String.format("Игрок %s подключился к прокси-серверу %s",
                playerName,
                proxyName));

        PlayerLoginEvent ple = new PlayerLoginEvent(op, proxy);
        ple.invoke();
    }

    @Override
    public int getId() {
        return Packets.PLAYER_LOGIN_PACKET;
    }
}

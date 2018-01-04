package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerPreLoginEvent;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class PlayerPreLoginPacket implements PacketIn {

    private String playerName;
    private String ip;
    private String proxyName;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        ip = PacketUtil.readString(in);
        proxyName = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        Proxy proxy = CoreServer.get().getProxies().getNode(proxyName);
        if (proxy == null) return;

        Runnable r = () -> {
            ServerPlayer player;
            player = PlayerFactory.loadPlayer(playerName);
            player.setIp(ip);
            player.setCurrentProxy(proxy);

            int id = player.getId();
            IdPacket idPacket = new IdPacket(playerName, id);
            idPacket.send(channel);

            if (player.isBanned()) {
                String reason = player.getBanReason();
                new KickPacket(playerName, reason).send(channel);
                Log.$(String.format("%s хотел войти, но забанен (%s)", playerName, reason));
                return;
            }

            CoreServer.get().getUnloggedPlayers().add(player);
            CoreServer.get().getOnlinePlayers().add(player);
            System.out.println(String.format("[+] Игрок %s подключился", playerName));

            PlayerPreLoginEvent prle = new PlayerPreLoginEvent(player);
            prle.invoke();
        };

        PlayerFactory.addTask(r);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PRELOGIN_PACKET;
    }
}

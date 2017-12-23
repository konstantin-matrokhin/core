package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerPreLoginEvent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class PlayerPreLoginPacket implements PacketIn {

    private String playerName;
    private String ip;
    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        ip = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer op = new ServerPlayer(playerName);
        op.setIp(ip);

        PlayerFactory.loadPlayer(op);

        Runnable r = () -> {
            try {
                Log.$("freeze..");

                PlayerFactory.queries.forEach(q -> {
                    try {
                        q.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                int id = op.getId();
                IdPacket idPacket = new IdPacket(playerName, id);
                idPacket.send(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(r).start();

        CoreServer.get().getUnloggedPlayers().add(op);
        System.out.println(String.format("Игрок %s подключился", playerName));

        PlayerPreLoginEvent prle = new PlayerPreLoginEvent(op);
        prle.invoke();
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PRELOGIN_PACKET;
    }
}

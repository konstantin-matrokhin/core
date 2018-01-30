package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class PreLoginPacket implements PacketIn {

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
        Proxy proxy = Core.get().getProxies().getNode(proxyName);
        if (proxy == null) return;

        Runnable r = () -> {
            ServerPlayer player;
            player = PlayerFactory.loadPlayer(playerName);
            player.setIp(ip);
            player.setCurrentProxy(proxy);
            Core.get().getUnloggedPlayers().add(player);

            int id = player.getId();
            IdPacket idPacket = new IdPacket(playerName, id);
            idPacket.send(channel);

            if (player.isBanned()) {
                if (player.getBannedUntil() > System.currentTimeMillis()) {
                    String reason = player.getBanReason();
                    new KickPacket(playerName, reason).send(channel);
                    Log.$(String.format("%s хотел войти, но забанен (%s)", playerName, reason));
                    return;
                } else {
                    PlayerFactory.unban(player);
                }
            }
        };

        PlayerFactory.addTask(r);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PRELOGIN_PACKET;
    }
}

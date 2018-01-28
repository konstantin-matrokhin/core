package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerSwitchServerEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerSwitchServerPacket implements PacketIn {

    private String playerName;
    private String to;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        to = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        PlayerFactory.addTask(() -> {
            ServerPlayer op = Core.get().getOnlinePlayer(playerName);
            if (op == null) return;

            GameServer destination = Core.get().getGameServers().getNode(to);

            if (op.getCurrentServer() != null) {
                op.getCurrentServer().getOnlinePlayers().remove(op);
            }

            op.setCurrentServer(destination);
            destination.getOnlinePlayers().add(op);

            PlayerSwitchServerEvent psse = new PlayerSwitchServerEvent(op, destination);
            psse.invoke();
        });
    }

    @Override
    public int getId() {
        return Packets.PLAYER_SWITCH_SERVER_PACKET;
    }
}

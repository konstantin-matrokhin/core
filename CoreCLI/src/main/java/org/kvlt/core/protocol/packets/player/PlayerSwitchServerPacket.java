package org.kvlt.core.protocol.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
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
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);
        if (op == null) return;

        GameServer destination = CoreServer.get().getGameServers().getNode(to);
        op.setCurrentServer(destination);

        PlayerSwitchServerEvent psse = new PlayerSwitchServerEvent(op, destination);
        psse.invoke();
    }

    @Override
    public int getId() {
        return Packets.PLAYER_SWITCH_SERVER_PACKET;
    }
}

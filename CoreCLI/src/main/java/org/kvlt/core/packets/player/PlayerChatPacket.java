package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.events.player.PlayerChatEvent;
import org.kvlt.core.events.player.PlayerCommandEvent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class PlayerChatPacket implements PacketIn {

    private String playerName;
    private String message;
    private boolean isCommand;

    @Override
    public void read(ByteBuf in) {
        this.playerName = PacketUtil.readString(in);
        this.message = PacketUtil.readString(in);
        this.isCommand = in.readBoolean();
    }

    @Override
    public void execute(Channel channel) {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);

        CoreEvent event;

        if (!isCommand) {
            event = new PlayerChatEvent(op, message);
        } else {
            event = new PlayerCommandEvent(op, message);
        }

        event.invoke();
    }

    @Override
    public int getId() {
        return 8;
    }
}

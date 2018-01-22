package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.utils.Log;

import static org.kvlt.core.protocol.Packets.PLAYER_REG_PACKET;

public class PlayerRegisterPacket extends PlayerPacket {

    private String name;
    private String password;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
        password = PacketUtil.readString(in);

        setPlayerName(name);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer unloggedPlayer = Core.get().getOnlinePlayers().get(name);
        if (unloggedPlayer == null) return;
        String dbPassword = unloggedPlayer.getPassword();

        if (dbPassword == null || dbPassword.isEmpty()) {
            PlayerFactory.register(unloggedPlayer, password);
            Log.$(String.format("%s зарегистрировался.", name));
            writeMsg("registration-successful");
        } else {
            writeMsg("already-registered");
        }

        AuthPacket authPacket = new AuthPacket(name);
        authPacket.send(channel);
        sendMsg(channel);
    }

    @Override
    public int getId() {
        return PLAYER_REG_PACKET;
    }
}

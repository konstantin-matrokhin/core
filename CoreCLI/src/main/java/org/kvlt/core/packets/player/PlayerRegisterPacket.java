package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.utils.Log;

import static org.kvlt.core.protocol.Packets.PLAYER_REG_PACKET;

public class PlayerRegisterPacket extends PlayerPacket {

    private ServerPlayer unloggedPlayer;
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
        unloggedPlayer = CoreServer.get().getUnloggedPlayers().get(name);
        if (unloggedPlayer == null) return;

        String response;
        String dbPassword = unloggedPlayer.getPassword();

        if (!ensurePlayer()) { // checking if player is logged in
            if (dbPassword == null || dbPassword.isEmpty()) {
                PlayerDB.register(unloggedPlayer, password);
                response = "Вы успешно зарегистировались!";
            } else {
                response = "Вы уже зарегистрированы!";
            }
        } else {
            response = "Вы уже в игре!";
        }

        MessagePacket msg = new MessagePacket(name, response);
        msg.send(channel);

    }

    @Override
    public int getId() {
        return PLAYER_REG_PACKET;
    }
}

package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.CoreDAO;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.PLAYER_REG_PACKET;

public class PlayerRegisterPacket extends PlayerPacket {

    private ServerPlayer unloggedPlayer;
    private String password;

    @Override
    public void read(ByteBuf in) {
        String name = PacketUtil.readString(in);
        setPlayerName(name);

        unloggedPlayer = new ServerPlayer(name);
        password = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        String response = null;

        if (!ensurePlayer()) { // checking if player is logged in
        } else {
            response = "Вы уже в игре!";
        }
        //TODO: send response
    }

    @Override
    public int getId() {
        return PLAYER_REG_PACKET;
    }
}

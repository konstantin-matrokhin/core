package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.CoreDAO;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketUtil;

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
        String response = null;
        unloggedPlayer = CoreServer.get().getUnloggedPlayers().get(name);
        if (unloggedPlayer == null) return;

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

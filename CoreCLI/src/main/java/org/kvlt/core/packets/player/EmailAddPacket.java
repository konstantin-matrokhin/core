package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.email.Email;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.CodeGenerator;

public class EmailAddPacket extends PlayerPacket {

    private String playerEmail;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        playerEmail = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {
            String code = CodeGenerator.genNiceCode().toUpperCase();
            getPlayer().setEmailConfirmationCode(code);
            PlayerFactory.updatePlayer(getPlayer());
            Email email = new Email(playerEmail);
            email.sendEmailConfirmation(getPlayer().getName(), code);
        } else {

        }
    }

    @Override
    public int getId() {
        return Packets.EMAIL_ADD_PACKET;
    }

}

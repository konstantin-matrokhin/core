package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.email.Email;
import org.kvlt.core.packets.MessagePacket;
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


    /**
     ADD:
         email | code | result
         null  | null | = allow
         yes   | yes  | = allow
         yes   | null | = disallow -- BOOLEAN ALLOWED
         null  | yes  | = allow
     */
    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {
            String response;
            boolean allowed = !(getPlayer().getEmailConfirmationCode() == null && getPlayer().getEmail() != null);
            if (!allowed) {
                String code = CodeGenerator.genNiceCode().toUpperCase();
                getPlayer().setEmailConfirmationCode(code);
                getPlayer().setEmail(playerEmail);
                PlayerFactory.updatePlayer(getPlayer());
                Email email = new Email(playerEmail);
                email.sendEmailConfirmation(getPlayer().getName(), code);
                response = "На ваш email %s отправлен код подтверждения\n" +
                        "Введите команду /email verify <код>";
            } else {
                response = "У вас уже привязан email!";
            }

            new MessagePacket(getPlayer().getName(), response).send(channel);
        }
    }

    @Override
    public int getId() {
        return Packets.EMAIL_ADD_PACKET;
    }

}

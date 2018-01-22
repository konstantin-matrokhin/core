package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.email.Email;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class PasswordRecoveryPacket extends PlayerPacket {

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        String name = getPlayer().getName();
        String email = getPlayer().getEmail();

        if (email != null && !email.isEmpty()) {
            if (getPlayer().getEmailConfirmationCode() == null) {
                Email recoveryMail = new Email(email);
                String hiddenEmail = email.substring(0, 2) + "***" +
                        email.substring(email.lastIndexOf("@") + 1);

                recoveryMail.sendPasswordRecovery(name, getPlayer().getPassword());
                Log.$(String.format("%s запросил пароль на %s",
                        name,
                        email));

                writeMsg("password-sent", hiddenEmail);
            } else {
                writeMsg("no-email-confirmed");
            }
        } else {
            writeMsg("no-email-confirmed");
        }

        sendMsg(channel);
    }

    @Override
    public int getId() {
        return Packets.PASSWORD_RECOVERY_PACKET;
    }

}

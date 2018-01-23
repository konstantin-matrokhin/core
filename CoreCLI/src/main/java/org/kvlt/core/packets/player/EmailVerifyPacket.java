package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.email.Email;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class EmailVerifyPacket extends PlayerPacket {

    private String code;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        code = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        PlayerFactory.addTask(() -> {
            if (!ensurePlayer()) return;

            String name = getPlayer().getName();
            String email = getPlayer().getEmail();
            String confirmationCode = getPlayer().getEmailConfirmationCode();

            if (Email.emailIsAvailable(email)) {
                if (confirmationCode != null && getPlayer().getEmailConfirmationCode().equalsIgnoreCase(code)) {
                    getPlayer().setEmailConfirmationCode(null);
                    getPlayer().setEmailConfirmed(true);
                    PlayerFactory.updatePlayer(getPlayer());

                    writeMsg("email-confirmed", email);
                } else {
                    writeMsg("incorrect-code");
                }
            } else {
                getPlayer().setEmail(null);
                writeMsg("email-not-available");
            }
            sendMsg(channel);
        });
    }

    @Override
    public int getId() {
        return Packets.EMAIL_VERIFY_PACKET;
    }

}

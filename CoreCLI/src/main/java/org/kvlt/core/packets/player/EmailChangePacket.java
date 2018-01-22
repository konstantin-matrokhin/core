package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.email.Email;
import org.kvlt.core.entities.EmailChangeData;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.CodeGenerator;

public class EmailChangePacket extends PlayerPacket {

    private String oldEmail;
    private String newEmail;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        oldEmail = PacketUtil.readString(in);
        newEmail = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        ServerPlayer player = getPlayer();
        String name = player.getName();

        if (player.isEmailConfirmed()) {
            if (player.getEmail().equalsIgnoreCase(oldEmail)) {
                String code1 = CodeGenerator.genNiceCode();
                String code2 = CodeGenerator.genNiceCode();
                new Email(oldEmail).sendChangeEmail(name, newEmail, code1);
                new Email(newEmail).sendEmailConfirmation(name, code2);

                EmailChangeData.setData(name, newEmail, code1, code2);
                writeMsg("email-change-sent");
            } else {
                writeMsg("incorrent-email");
            }
        } else {
            writeMsg("no-email-confirmed");
        }

        sendMsg(channel);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_CHANGE_PACKET;
    }
}

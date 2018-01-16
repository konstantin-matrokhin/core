package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.packets.MessagePacket;
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
        if (!ensurePlayer()) return;

        String name = getPlayer().getName();
        String email = getPlayer().getEmail();
        String response;
        String confirmationCode = getPlayer().getEmailConfirmationCode();

        if (confirmationCode != null && getPlayer().getEmailConfirmationCode().equalsIgnoreCase(code)) {
            getPlayer().setEmailConfirmationCode(null);
            getPlayer().setEmailConfirmed(true);
            PlayerFactory.updatePlayer(getPlayer());
            response = String.format("Email %s успешно привязан!", email);
        } else {
            response = "Введен неверный код!";
        }

        MessagePacket responsePacket = new MessagePacket(name, response);
        responsePacket.send(channel);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_VERIFY_PACKET;
    }

}

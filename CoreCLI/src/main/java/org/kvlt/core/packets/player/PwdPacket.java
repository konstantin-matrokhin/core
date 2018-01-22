package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.email.Email;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PwdPacket extends PlayerPacket {

    private String oldPassword;
    private String newPassword;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        oldPassword = PacketUtil.readString(in);
        newPassword = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        String name = getPlayer().getName();
        String playerPassword = getPlayer().getPassword();

        if (playerPassword.equals(oldPassword)) {
            getPlayer().setPassword(newPassword);
            PlayerFactory.updatePlayer(getPlayer());

            String playerEmail = getPlayer().getEmail();
            if (playerEmail != null) {
                Email email = new Email(playerEmail);
                email.sendPasswordChange(name);
            }
            writeMsg("password-changed");
        } else {
            writeMsg("incorrect-password");
        }
        sendMsg(channel);
    }

    @Override
    public int getId() {
        return Packets.PWD_PACKET;
    }

}

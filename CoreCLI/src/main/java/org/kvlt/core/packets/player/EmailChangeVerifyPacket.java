package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.entities.EmailChangeData;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

import java.util.List;

public class EmailChangeVerifyPacket extends PlayerPacket {

    private String code1;
    private String code2;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        code1 = PacketUtil.readString(in);
        code2 = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        ServerPlayer player = getPlayer();
        String name = player.getName();
        String email = player.getEmail();

        if (player.isEmailConfirmed()) {
            if (EmailChangeData.isChanging(name)) {
                List<String> data = EmailChangeData.getData(name);
                boolean validCodes = data.get(1).equalsIgnoreCase(code1) && data.get(2).equalsIgnoreCase(code2);
                if (validCodes) {
                    String newEmail = data.get(0);
                    EmailChangeData.removePlayer(name);
                    player.setEmail(newEmail);

                    writeMsg("email-change-success", newEmail);
                } else {
                    writeMsg("incorrect-codes");
                }
            } else {
                writeMsg("no-change-query");
            }
         } else {
            writeMsg("no-email-confirmed");
        }

        sendMsg(channel);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_CHANGE_VERIFY_PACKET;
    }
}

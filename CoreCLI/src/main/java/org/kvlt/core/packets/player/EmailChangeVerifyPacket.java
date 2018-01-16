package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.entities.EmailChangeData;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
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
        String response;

        if (player.isEmailConfirmed()) {
            if (EmailChangeData.isChanging(name)) {
                List<String> data = EmailChangeData.getData(name);
                boolean validCodes = data.get(1).equalsIgnoreCase(code1) && data.get(2).equalsIgnoreCase(code2);
                if (validCodes) {
                    String newEmail = data.get(0);
                    EmailChangeData.removePlayer(name);
                    player.setEmail(newEmail);

                    response = String.format("Вы успешно сменили email на %s", newEmail);
                } else {
                    response = "Неверные коды!";
                }
            } else {
                response = "Вы не запросили смену почты!";
            }
         } else {
            response = "Сначала привяжите email!";
        }

        new MessagePacket(name, response).send(channel);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_CHANGE_VERIFY_PACKET;
    }
}

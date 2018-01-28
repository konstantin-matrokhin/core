package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.EventResult;
import org.kvlt.core.events.player.PlayerJoinEvent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class LoginPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        PlayerFactory.addTask(() -> {
            ServerPlayer player = Core.get().getUnloggedPlayer(name);
            if (player != null) {
                PlayerJoinEvent event = new PlayerJoinEvent(player);
                event.invoke();

                if (event.isCancelled()) {
                    event.setResult(EventResult.KICK, "Доступ ограничен.");
                    return;
                }

                player.setJoinTime(System.currentTimeMillis());
                PlayerFactory.updatePlayer(player);
                System.out.println(String.format("[+] Игрок %s подкючился", name));
            } else {
                System.out.println(String.format("%s is null!", name));
            }
        });
    }

    @Override
    public int getId() {
        return Packets.PLAYER_LOGIN_PACKET;
    }
}

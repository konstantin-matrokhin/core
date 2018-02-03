package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.storages.IpBanlist;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IpBanPacket implements PacketIn {

    private String ip;

    @Override
    public void read(ByteBuf in) {
        ip = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        long untilMillis = IpBanlist.ban(ip);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(15);

        ProxyServer.getInstance().getScheduler().runAsync(CoreBungee.getPlugin(), () -> {
            ProxyServer.getInstance().getPlayers()
                    .parallelStream()
                    .forEach(p -> {
                        if (p.getAddress().getHostName().equalsIgnoreCase(ip)) {
                            p.disconnect(new TextComponent(String.format("ip ban for %d minutes", minutes)));
                        }
                    });
        });
    }

    @Override
    public int getId() {
        return Packets.BAN_IP;
    }

}

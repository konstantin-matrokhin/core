package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.Group;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BanPacket implements PacketIn {

    private String enforcer;
    private String victim;
    private String time;
    private String reason;

    @Override
    public void read(ByteBuf in) {
        enforcer = PacketUtil.readString(in);
        victim = PacketUtil.readString(in);
        time = PacketUtil.readString(in);
        reason = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        String response;
        int level;

        if (!enforcer.equalsIgnoreCase("console")) {
            ServerPlayer enforcerPlayer = CoreServer.get().getOnlinePlayers().get(enforcer);
            if (enforcerPlayer != null) {
                level = enforcerPlayer.getGroup();
            } else {
                return;
            }
        } else {
            level = 1000;
        }

        if (level > Group.HELPER.getId()) {
            ServerPlayer victimPlayer;
            if (CoreServer.get().getOnlinePlayers().contains(victim)) {
                victimPlayer = CoreServer.get().getOnlinePlayers().get(victim);
            } else {
                victimPlayer = PlayerFactory.loadPlayer(victim, false);
                if (victimPlayer != null) {
                    long parsedTime = PlayedTimeCounter.parseTime(time);
                    victimPlayer.setBanned(true);
                    victimPlayer.setBannedBy(enforcer);
                    victimPlayer.setBannedUntil(parsedTime);
                    victimPlayer.setBanAmount(victimPlayer.getBanAmount() + 1);
                    victimPlayer.setBanReason(reason);
                    PlayerFactory.updatePlayer(victimPlayer);
                } else {
                    response = "Нет такого игрока в базе!";
                }
            }
            response = "Успешно!";
        } else {
            response = "Недостаточно прав!";
        }

        MessagePacket responsePacket = new MessagePacket(enforcer, response);
        responsePacket.send(channel);
    }

    @Override
    public int getId() {
        return Packets.BAN_PACKET;
    }

}

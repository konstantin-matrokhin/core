package org.kvlt.core.packets.player;

import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.PacketOld;

public class PlayerSwitchServerPacketOld extends PacketOld<Channel> {

    private String playerName;
    private String to;

    public PlayerSwitchServerPacketOld(String playerName, String to) {
        this.playerName = playerName;
        this.to = to;
    }

    @Override
    public void onCore() {

    }

    @Override
    protected void onCore(Channel channel) {
        OnlinePlayer p = CoreServer.get().getOnlinePlayers().get(playerName);
        if (p == null) return;

        if (p.isLogged()) {
            PlayerAuthPacketOld pap = new PlayerAuthPacketOld(p.getName(), true);
            CoreServer.get().getGameServers().getNode(to).send(pap);
        }

        if (p.getCurrentServer() != null) {
            if (p.isLogged()) {
                PlayerAuthPacketOld pap = new PlayerAuthPacketOld(playerName, true);
                CoreServer.get().getGameServers().send(pap);
            } else {
                return;
            }
        }
        p.setCurrentServer(CoreServer.get().getGameServers().getNode(to));
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}

package org.kvlt.core.packets.player;

import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

public class PlayerSwitchServerPacket extends Packet<Channel> {

    private String playerName;
    private String to;

    public PlayerSwitchServerPacket(String playerName, String to) {
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
            PlayerAuthPacket pap = new PlayerAuthPacket(p.getName(), true);
            CoreServer.get().getGameServers().getNode(to).send(pap);
        }

        if (p.getCurrentServer() == null) {
            System.out.println(playerName + " зашел на сервер " + to);
        } else {
            if (p.isLogged()) {
                PlayerAuthPacket pap = new PlayerAuthPacket(playerName, true);
                CoreServer.get().getGameServers().send(pap);
                System.out.println(playerName + " сменил сервер с " + p.getCurrentServer().getName() + " на " + to);
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

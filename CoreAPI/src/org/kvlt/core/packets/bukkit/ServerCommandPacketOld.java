package org.kvlt.core.packets.bukkit;

import org.kvlt.core.CoreServer;
import org.kvlt.core.bukkit.corehandlers.CoreHandler;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.PacketOld;
import org.kvlt.core.packets.type.Spigot;

@Spigot
public class ServerCommandPacketOld extends PacketOld {

    private String sender;
    private String to;
    private String cmd;

    public ServerCommandPacketOld(String sender, String to, String cmd) {
        this.sender = sender;
        this.to = to;
        this.cmd = cmd;
    }

    @Override
    protected void onCore() {
        OnlinePlayer player = CoreServer.get().getOnlinePlayers().get(sender);
        GameServer toServer = CoreServer.get().getGameServers().getNode(to);

        if (player == null) return;
        if (player.getGroup() < 1) return;

        if (toServer != null) {
            toServer.send(this);
        }
    }

    @Override
    protected void onServer() {
        CoreHandler.executeServerCommand(cmd);
    }

    @Override
    protected void onProxy() {

    }
}

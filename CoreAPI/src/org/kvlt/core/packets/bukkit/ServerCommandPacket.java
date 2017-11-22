package org.kvlt.core.packets.bukkit;

import org.bukkit.Bukkit;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.bukkit.corehandlers.CoreHandler;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Packet;

public class ServerCommandPacket extends Packet {

    private String sender;
    private String to;
    private String cmd;

    public ServerCommandPacket(String sender, String to, String cmd) {
        this.sender = sender;
        this.to = to;
        this.cmd = cmd;
    }

    @Override
    protected void onCore() {
        GameServer toServer = CoreServer.get().getGameServers().getNode(to);

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

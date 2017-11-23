package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

public class StaffMessage extends Packet {

    static final String TEMPLATE = "(STAFF) [%name%]: %msg%";

    private String senderName;
    private String message;

    public StaffMessage(String senderName, String message) {
        this.senderName = senderName;
        this.message = message;
    }

    @Override
    protected void onCore() {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(senderName);
        int group = op.getGroup();

        if (group > 1) {
            CoreServer.get().getGameServers().send(this);
        }
    }

    @Override
    protected void onServer() {
        //TODO: logic
    }

    @Override
    protected void onProxy() {

    }
}

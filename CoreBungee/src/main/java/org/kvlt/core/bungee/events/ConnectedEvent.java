package org.kvlt.core.bungee.events;

import io.netty.channel.Channel;
import net.md_5.bungee.api.plugin.Event;

public class ConnectedEvent extends Event {

    private final Channel serverChannel;

    public ConnectedEvent(Channel serverChannel) {
        this.serverChannel = serverChannel;
    }

    public Channel getServerChannel() {
        return serverChannel;
    }

}

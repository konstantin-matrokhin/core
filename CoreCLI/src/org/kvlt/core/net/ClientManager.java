package org.kvlt.core.net;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ClientManager {

    private static ChannelGroup clients;

    static {
        clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    public static ChannelGroup getClients() {
        return clients;
    }

    public static void add(Channel channel) {
        clients.add(channel);
    }

    public static void remove(Channel channel) {
        clients.remove(channel);
    }

    public static void sendToAll(Object obj) {
        clients.writeAndFlush(obj);
    }

}

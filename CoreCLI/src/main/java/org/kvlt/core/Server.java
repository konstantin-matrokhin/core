package org.kvlt.core;

import org.kvlt.core.protocol.PacketResolver;

public interface Server {

    void start();

    void stop();

    void restart();

    int getPort();

    PacketResolver getPacketResolver();

}

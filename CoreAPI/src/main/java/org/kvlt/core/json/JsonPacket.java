package org.kvlt.core.json;

import org.kvlt.core.protocol.PacketIn;

public interface JsonPacket extends PacketIn {

    String getJson();

}

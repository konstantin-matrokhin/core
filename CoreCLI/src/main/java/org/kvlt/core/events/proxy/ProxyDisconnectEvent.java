package org.kvlt.core.events.proxy;

import org.kvlt.core.nodes.Proxy;

public class ProxyDisconnectEvent extends ProxyConnectEvent {

    public ProxyDisconnectEvent() {
        super();
    }

    public ProxyDisconnectEvent(Proxy proxy) {
        super(proxy);
    }
}

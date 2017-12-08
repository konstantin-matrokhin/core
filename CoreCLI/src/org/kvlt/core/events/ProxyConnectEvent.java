package org.kvlt.core.events;

import org.kvlt.core.nodes.Proxy;

public class ProxyConnectEvent implements CoreEvent {

    public ProxyConnectEvent() {}

    public ProxyConnectEvent(Proxy proxy) {
        this.proxy = proxy;
    }

    private Proxy proxy;

    public Proxy getProxy() {
        return proxy;
    }

}

package org.kvlt.core.events.proxy;

import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.nodes.Proxy;

public class ProxyConnectEvent implements CoreEvent {

    public ProxyConnectEvent(Proxy proxy) {
        this.proxy = proxy;
    }

    private Proxy proxy;

    public Proxy getProxy() {
        return proxy;
    }

}

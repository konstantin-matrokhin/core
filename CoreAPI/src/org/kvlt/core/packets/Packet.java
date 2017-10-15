package org.kvlt.core.packets;

import java.io.Serializable;

public abstract class Packet implements Serializable {

    private boolean isCore() {
        try {
            Class.forName("org.kvlt.core.CoreCLI");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isServer() {
        return false;
    }

    private boolean isProxy() {
        return false;
    }

    protected abstract void onCore();
    protected abstract void onServer();
    protected abstract void onProxy();

    public void execute() {
        if (isCore()) {
            onCore();
        } else { //TODO: check if is bukkit server
            onServer();
        }

        if (isProxy()) {
            onProxy();
        }
    }

}

package org.kvlt.core.packets;

import java.io.Serializable;

public abstract class Packet<T> implements Serializable {

    private static final String CORE_CLASS = "org.kvlt.core.CoreCLI";
    private static final String SERVER_CLASS = "org.kvlt.core.bukkit.CorePlugin";
    private static final String PROXY_CLASS = "org.kvlt.core.bungee.CoreBungee";

    private boolean isCore() {
        return checkClass(CORE_CLASS);
    }

    private boolean isServer() {
        return checkClass(SERVER_CLASS);
    }

    private boolean isProxy() {
        return checkClass(PROXY_CLASS);
    }

    protected abstract void onCore() throws Exception;
    protected abstract void onServer() throws Exception;
    protected abstract void onProxy() throws Exception;

    protected void onCore(T t) throws Exception {

    }

    public void execute(T t) {
        try {
            if (isCore()) {
                onCore(t);
                onCore();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        try {
            if (isServer()) {
                onServer();
            }

            if (isProxy()) {
                onProxy();
            }
        } catch (Exception e) {

        }
    }

    private boolean checkClass(String apiClass) {
        try {
            Class.forName(apiClass);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

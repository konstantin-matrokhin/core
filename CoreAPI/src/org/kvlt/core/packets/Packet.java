package org.kvlt.core.packets;

import java.io.Serializable;

/**
 * Пакет - абстрактный класс, методы которого могут исполняться как на сервере, так и на клиенте
 * Если передан шаблон, то контекст будет передан главному серверу. Обычно используется Channel для обратной связи
 *
 * Чтобы отправить пакет, запишите его экземпляр в буфер канала и просто отправьте
 * <b>Channel#writeAndFlush(Packet packet);</b>
 *
 * Обрабатывайте данные в onCore, onServer, onProxy в зависимости от того, куда пришел пакет
 *
 */
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

    protected abstract void onCore();
    protected abstract void onServer();
    protected abstract void onProxy();

    protected void onCore(T t) {}

    public void execute(T t) {
        if (isCore()) {
            onCore(t);
            onCore();
        }
    }

    public void execute() {
        if (isServer()) {
            onServer();
        }

        if (isProxy()) {
            onProxy();
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

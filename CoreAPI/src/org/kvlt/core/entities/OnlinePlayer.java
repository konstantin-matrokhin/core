package org.kvlt.core.entities;

import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.nodes.Proxy;

/**
 * Класс, который является сущностью игрока на сервере
 */
public class OnlinePlayer extends ServerPlayer {

    private GameServer currentServer;
    private Proxy currentProxy;
    private String ip;
    private long joinTime;
    private long leaveTime;

    public GameServer getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(GameServer currentServer) {
        this.currentServer = currentServer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Proxy getCurrentProxy() {
        return currentProxy;
    }

    public void setCurrentProxy(Proxy currentProxy) {
        this.currentProxy = currentProxy;
    }
}

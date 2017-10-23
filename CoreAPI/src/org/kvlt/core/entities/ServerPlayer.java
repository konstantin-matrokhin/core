package org.kvlt.core.entities;

import org.kvlt.core.nodes.GameServer;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

/**
 * Абстрактный класс, от которого наследуются все классы, хранящие данные об игроке
 */
public abstract class ServerPlayer implements Serializable {

    private String name;
    private UUID uuid;
    private int id;
    private Group group;
    private String lastIp;
    private String lastJoin;
    private GameServer lastServer;
    private boolean banned;
    private boolean muted;
    private Date playedTime;

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return  uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(String lastJoin) {
        this.lastJoin = lastJoin;
    }

    public GameServer getLastServer() {
        return lastServer;
    }

    public void setLastServer(GameServer lastServer) {
        this.lastServer = lastServer;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public Date getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(Date playedTime) {
        this.playedTime = playedTime;
    }
}

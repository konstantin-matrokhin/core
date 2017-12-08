package org.kvlt.core.entities;

import org.kvlt.core.nodes.GameServer;

import java.io.Serializable;

/**
 * Абстрактный класс, от которого наследуются все классы, хранящие данные об игроке
 */
public abstract class ServerPlayer implements Serializable {

    private int id;
    private String name;
    private String password;
    private int group;
    private String uuid;
    private String lastIp;
    private String lastJoin;
    private GameServer lastServer;
    private String mutedBy;
    private String bannedBy;
    private boolean banned;
    private boolean muted;
    private int muteAmount;
    private int banAmount;
    private long bannedUntil;
    private long mutedUntil;
    private String banReason;
    private long playedLastTime;
    private long playedTotal;
    private boolean isRegistered;

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getMutedBy() {
        return mutedBy;
    }

    public void setMutedBy(String mutedBy) {
        this.mutedBy = mutedBy;
    }

    public String getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(String bannedBy) {
        this.bannedBy = bannedBy;
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

    public int getMuteAmount() {
        return muteAmount;
    }

    public void setMuteAmount(int muteAmount) {
        this.muteAmount = muteAmount;
    }

    public int getBanAmount() {
        return banAmount;
    }

    public void setBanAmount(int banAmount) {
        this.banAmount = banAmount;
    }

    public long getBannedUntil() {
        return bannedUntil;
    }

    public void setBannedUntil(long bannedUntil) {
        this.bannedUntil = bannedUntil;
    }

    public long getMutedUntil() {
        return mutedUntil;
    }

    public void setMutedUntil(long mutedUntil) {
        this.mutedUntil = mutedUntil;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public long getPlayedLastTime() {
        return playedLastTime;
    }

    public void setPlayedLastTime(long playedLastTime) {
        this.playedLastTime = playedLastTime;
    }

    public long getPlayedTotal() {
        return playedTotal;
    }

    public void setPlayedTotal(long playedTotal) {
        this.playedTotal = playedTotal;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}

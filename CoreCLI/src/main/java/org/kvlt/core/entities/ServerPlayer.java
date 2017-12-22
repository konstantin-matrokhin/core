package org.kvlt.core.entities;

import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.player.KickPacket;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "identifier")
@SecondaryTables({
        @SecondaryTable(name = "authentication", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
})
public class ServerPlayer implements Serializable, Kickable {

    public ServerPlayer(String name) {
        this.name = name;
    }

    public ServerPlayer() {
    }

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "player_name")
    private String name;

    @Column(table = "authentication", name = "password", updatable = true, insertable = true)
    private String password;

    @Transient
    private int group;
    @Transient
    private String uuid;
    @Transient
    private String lastIp;
    @Transient
    private String lastJoin;
    @Transient
    private GameServer lastServer;
    @Transient
    private String mutedBy;
    @Transient
    private String bannedBy;
    @Transient
    private boolean muted;
    @Transient
    private boolean banned;
    @Transient
    private int muteAmount;
    @Transient
    private int banAmount;
    @Transient
    private long bannedUntil;
    @Transient
    private long mutedUntil;
    @Transient
    private String banReason;
    @Transient
    private String muteReason;
    @Transient
    private long playedLastTime;
    @Transient
    private long playedTotal;
    @Transient
    private boolean isRegistered;
    @Transient
    private long leaveTime;
    @Transient
    private long joinTime;
    @Transient
    private String ip;

    public void kick(String reason) {
        new KickPacket(this.name, reason).send();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerPlayer)) return false;
        ServerPlayer that = (ServerPlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

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

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public void setMuteReason(String muteReason) {
        this.muteReason = muteReason;
    }
}

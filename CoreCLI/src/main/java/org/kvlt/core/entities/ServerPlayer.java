package org.kvlt.core.entities;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.player.KickPacket;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "identifier", uniqueConstraints = @UniqueConstraint(columnNames = "player_name"))
@SecondaryTables({
        @SecondaryTable(name = "authentication",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "join_info",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "infractions",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "players_groups",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "premium_auth",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "reports",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "friends",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "ignores",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "custom_prefixes",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "selected_skins",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")),
        @SecondaryTable(name = "localization", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id"))
})
public class ServerPlayer implements Serializable, Kickable {

    public ServerPlayer(String name) {
        this.name = name;
    }

    public ServerPlayer() {}

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GenericGenerator(name = "id_gen", strategy = "increment")
    @GeneratedValue(generator = "id_gen")
    private int id;

    @Column(table = "identifier", name = "player_name", unique = true, length = 32, nullable = false)
    private String name;

    @Column(table = "authentication", name = "password", length = 32)
    private String password;

    @Column(table = "players_groups", name = "group_id")
    private int group;

    @Column(table = "localization", name = "lang")
    private String lang = "russian";

    @Column(table = "join_info", name = "ip", length = 32)
    private String lastIp = "empty";

    @Column(table = "authentication", name = "last_authenticated")
    private long lastAuth = -1;

    @Column(table = "join_info", name = "last_server", length = 32)
    private String lastServer;

    @Column(table = "infractions", name = "mute_enforcer", length = 16)
    private String mutedBy;

    @Column(table = "infractions", name = "ban_enforcer", length = 16)
    private String bannedBy;

    @Column(table = "infractions", name = "is_muted")
    private boolean muted;

    @Column(table = "infractions", name = "is_banned")
    private boolean banned;

    @Column(table = "infractions", name = "mutes")
    private int muteAmount;

    @Column(table = "infractions", name = "bans")
    private int banAmount;

    @Column(table = "infractions", name = "ban_end")
    private long bannedUntil;

    @Column(table = "infractions", name = "mute_end")
    private long mutedUntil;

    @Column(table = "infractions", name = "ban_reason", length = 64)
    private String banReason;

    @Column(table = "infractions", name = "mute_reason", length = 64)
    private String muteReason;

    @Column(table = "join_info", name = "last_online")
    private long playedLastTime;

    @Column(table = "join_info", name = "online_time")
    private long playedTotal;

    @Column(table = "authentication", name = "registration_ip", length = 32)
    private String registerIp;

    @Column(table = "authentication", name = "email", length = 64)
    private String email;

    @Column(table = "authentication", name = "email_confirmed")
    private boolean isEmailConfirmed;

    @Column(table = "authentication", name = "email_confirmation_code", length = 32)
    private String emailConfirmationCode;

    @Column(table = "authentication", name = "email_code_timestamp")
    private long emailTimestamp;

    @Column(table = "custom_prefixes", name = "prefix", length = 32)
    private String prefix;

    @Transient
//    @ElementCollection
//    @Column(table = "friends", name = "friend_id")
    private transient Set<Integer> friends;

    @Transient
//    @ElementCollection
//    @Column(table = "ignores", name = "ignored_id")
    private transient Set<Integer> ignores;

    @Column(table = "reports", name = "total_reports")
    private int totalReports;

    @Column(table = "reports", name = "reports_approved")
    private int reportsApproved;

    @Column(table = "selected_skins", name = "skin", length = 16)
    private String skin;

    @Transient
    private boolean isRegistered;

    @Transient
    private long leaveTime;

    @Transient
    private long joinTime;

    @Transient
    private String ip;

    @Transient
    private boolean isOnline;

    @Transient
    private GameServer currentServer;

    @Transient
    private Proxy currentProxy;

    public void kick(String reason) {
        new KickPacket(this.name, reason).send(Destination.BUNGEE);
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

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public GameServer getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(GameServer currentServer) {
        this.currentServer = currentServer;
    }

    public Proxy getCurrentProxy() {
        return currentProxy;
    }

    public void setCurrentProxy(Proxy currentProxy) {
        this.currentProxy = currentProxy;
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

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public long getLastAuth() {
        return lastAuth;
    }

    public void setLastAuth(long lastAuth) {
        this.lastAuth = lastAuth;
    }

    public String getLastServer() {
        return lastServer;
    }

    public void setLastServer(String lastServer) {
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

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public String getEmailConfirmationCode() {
        return emailConfirmationCode;
    }

    public void setEmailConfirmationCode(String emailConfirmationCode) {
        this.emailConfirmationCode = emailConfirmationCode;
    }

    public long getEmailTimestamp() {
        return emailTimestamp;
    }

    public void setEmailTimestamp(long emailTimestamp) {
        this.emailTimestamp = emailTimestamp;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Integer> friends) {
        this.friends = friends;
    }

    public Set<Integer> getIgnores() {
        return ignores;
    }

    public void setIgnores(Set<Integer> ignores) {
        this.ignores = ignores;
    }

    public int getTotalReports() {
        return totalReports;
    }

    public void setTotalReports(int totalReports) {
        this.totalReports = totalReports;
    }

    public int getReportsApproved() {
        return reportsApproved;
    }

    public void setReportsApproved(int reportsApproved) {
        this.reportsApproved = reportsApproved;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}

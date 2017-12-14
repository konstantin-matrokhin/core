package org.kvlt.core.models;

public class InfractionsModel implements Model {

    private int id;
    private long muteEnd;
    private String muteReason;
    private String muteEnforcer;
    private int mutes;

    private long banEnd;
    private String banReason;
    private String banEnforcer;
    private int bans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMuteEnd() {
        return muteEnd;
    }

    public void setMuteEnd(long muteEnd) {
        this.muteEnd = muteEnd;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public void setMuteReason(String muteReason) {
        this.muteReason = muteReason;
    }

    public String getMuteEnforcer() {
        return muteEnforcer;
    }

    public void setMuteEnforcer(String muteEnforcer) {
        this.muteEnforcer = muteEnforcer;
    }

    public int getMutes() {
        return mutes;
    }

    public void setMutes(int mutes) {
        this.mutes = mutes;
    }

    public long getBanEnd() {
        return banEnd;
    }

    public void setBanEnd(long banEnd) {
        this.banEnd = banEnd;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public String getBanEnforcer() {
        return banEnforcer;
    }

    public void setBanEnforcer(String banEnforcer) {
        this.banEnforcer = banEnforcer;
    }

    public int getBans() {
        return bans;
    }

    public void setBans(int bans) {
        this.bans = bans;
    }
}

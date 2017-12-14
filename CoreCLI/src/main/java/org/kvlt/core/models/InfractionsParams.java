package org.kvlt.core.models;

public class InfractionsParams extends ModelParams {

    @Override
    public String selectSQL() {
        return "SELECT * FROM infractions WHERE id = :id";
    }

    @Override
    protected void fillCols() {
        map("mute_end", "muteEnd");
        map("mute_reason", "muteReason");
        map("mute_enforcer", "muteEnforcer");
        map("ban_end", "banEnd");
        map("ban_reason", "banReason");
        map("ban_enforcer", "banEnforcer");
    }

}

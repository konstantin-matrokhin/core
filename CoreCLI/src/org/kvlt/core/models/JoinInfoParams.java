package org.kvlt.core.models;

public class JoinInfoParams extends ModelParams {

    @Override
    public String selectSQL() {
        return "SELECT * FROM join_info WHERE id = :id";
    }

    @Override
    protected void fillCols() {
        map("online_time", "onlineTime");
        map("last_online", "lastOnline");
    }
}

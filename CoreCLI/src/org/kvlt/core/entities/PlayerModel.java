package org.kvlt.core.entities;

import org.kvlt.core.db.DAO;

public class PlayerModel extends ServerPlayer {

    private int id;
    private String ip;
    private String server;
    private long onlineTime;
    private long lastOnline;

    public PlayerModel(String name) {

    }

    public static void create(String name) {
        String loadId = "SELECT id FROM identifiers WHERE player_name = :name";
        DAO.getConnection()
                .createQuery(loadId)
                .addParameter("name", name)
                .executeAndFetchFirst(ServerPlayer.class);
    }

}

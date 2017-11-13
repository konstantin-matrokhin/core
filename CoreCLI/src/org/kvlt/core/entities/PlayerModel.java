package org.kvlt.core.entities;

import org.kvlt.core.db.DAO;

public class PlayerModel extends ServerPlayer {

    public PlayerModel(String name) {
        String loadId = "SELECT id FROM identifiers WHERE player_name = :name";
        DAO.getConnection()
                .createQuery(loadId)
                .addParameter("name", name)
                .executeAndFetchFirst(ServerPlayer.class);
    }

}

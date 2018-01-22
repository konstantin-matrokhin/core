package org.kvlt.core.entities;

import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.kvlt.core.db.PlayerFactory;

import java.util.*;

public class PremiumPlayers {

    private static final String SELECT_SQL =
            "SELECT\n" +
            "identifier.id,\n" +
            "identifier.player_name\n" +
            "FROM\n" +
            "identifier INNER JOIN premium_auth\n" +
            "ON\n" +
            "premium_auth.id = identifier.id;";

    private static final String INSERT_QUERY =
            "INSERT INTO premium_auth (id) VALUES (:id)";

    private Session session;
    private Map<Integer, String> map;

    public PremiumPlayers(Session session) {
        this.session = session;

        map = new HashMap<>();
    }

    public void loadFromMySQL() {
        List data = session
                .createNativeQuery(SELECT_SQL)
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("player_name", StandardBasicTypes.STRING)
                .list();

        for (Object aData : data) {
            Object[] row = (Object[]) aData;

            int id = (int) row[0];
            String name = (String) row[1];
            map.put(id, name);
        }
    }

    public void add(ServerPlayer player) {
        int id = player.getId();
        String name = player.getName();
        map.put(id, name);

        PlayerFactory.addTask(() -> {
            session
                .createNativeQuery(INSERT_QUERY)
                .setParameter("id", id)
                .executeUpdate();
            System.out.println("task ran");
        });
    }

    public int[] ids() {
        return map.keySet().stream().mapToInt(Integer::intValue).toArray();
    }

    public String[] names() {
        return map.values().toArray(new String[map.size()]);
    }

    public Map<Integer, String> map() {
        return map;
    }

}

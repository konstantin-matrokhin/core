package org.kvlt.core.entities;

import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

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

        Iterator rows = data.iterator();
        while (rows.hasNext()) {
            Object[] row = (Object[]) rows.next();

            int id = (int) row[0];
            String name = (String) row[1];
            map.put(id, name);
        }
    }

    public void add(ServerPlayer player) {
        int id = player.getId();
        String name = player.getName();
        map.put(id, name);
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

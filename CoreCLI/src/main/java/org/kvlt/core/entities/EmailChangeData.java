package org.kvlt.core.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailChangeData {

    // List = NAME, CODE_1, CODE_2
    private static Map<String, List<String>> emailData;

    static {
        emailData = new HashMap<>();
    }

    public static void setData(String player, String email, String code1, String code2) {
        emailData.put(player, Arrays.asList(email, code1, code2));
    }

    public static List<String> getData(String player) {
        return emailData.get(player);
    }

    public static boolean isChanging(String player) {
        List<String> key = emailData.get(player);
        return key != null && key.size() == 3;
    }

    public static void removePlayer(String player) {
        emailData.remove(player);
    }

}

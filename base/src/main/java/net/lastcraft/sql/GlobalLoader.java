package net.lastcraft.sql;

import net.lastcraft.sql.api.LastCraftSQL;
import net.lastcraft.sql.api.SQLConnection;
import net.lastcraft.util.Pair;
import net.lastcraft.group.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GlobalLoader {

    private static SQLConnection connection;

    static  {
        connection = LastCraftSQL.createConnect("build" +
                Connection.domane, "root", "12345", "newcore");
    }

    public static SQLConnection getConnection() {
        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (Exception ignored) {
        }
    }

    public static List<Integer> getFriendsID(int playerID) {
        List<Integer> friends = new ArrayList<>();
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `friends` WHERE `id` = '" + playerID + "';");
            while (rs.next()) {
                int friend = rs.getInt("friend_id");
                friends.add(friend);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return friends;
    }

    public static int getPlayerID(String name) {
        int playerID = -1;
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet id = statement.executeQuery("SELECT `id` FROM `identifier` WHERE `player_name` = '" + name + "' LIMIT 1;");
            if (id.next()) {
                playerID = id.getInt(1);
            }
            id.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return playerID;
    }

    public static void setPrefix(int playerID, String value) {
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `prefix` FROM `custom_prefixes` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                connection.execute("UPDATE `custom_prefixes` SET `prefix` = '" + value + "' WHERE `id` = '" + playerID + "';");
            } else {
                connection.execute("INSERT IGNORE INTO `custom_prefixes` (`id`, `prefix`) VALUES ('" + playerID + "', '" + value + "');");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String getPrefix(int playerID, Group group) {
        String prefix = group.getPrefix();
        if (group != Group.SHULKER && group != Group.ADMIN) {
            return prefix;
        }
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `prefix` FROM `custom_prefixes` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                String charPrefix = rs.getString("prefix");
                if (charPrefix.length() > 1) {
                    prefix = prefix.replaceAll("§[0-9a-e]", charPrefix.replaceAll("&", "§"));
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prefix;
    }

    public static String getPrefixChar(int playerID) {
        String charPrefix = "";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `prefix` FROM `custom_prefixes` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                charPrefix = rs.getString("prefix");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return charPrefix;
    }

    public static void setSetting(int playerID, int type, int value) {
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `setting_value` FROM `settings` WHERE `id` = '" + playerID + "' AND `setting_id` = '" + type +"';");
            if (rs.next()) {
                connection.execute("UPDATE `settings` SET `setting_value` = '" + value + "' WHERE `id` = '" + playerID + "' AND `setting_id` = '" + type + "';");
            } else {
                connection.execute("INSERT IGNORE INTO `settings` (`setting_id`, `setting_value`, `id`) VALUES ('" + type + "', '" + value + "', '" + playerID + "');");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Pair<Integer, Integer> getPlayerMoney(int playerID) {
        int money = 0;
        int gold = 0;
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `money` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                money = rs.getInt("balance");
                gold = rs.getInt("gold");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new Pair<>(money, gold);
    }

    public static String getSelectedSkin(String name, int playerID) {
        String value = "BlyatSkin";
        try {
            Statement statement = connection.getConnection().createStatement();

            String select = null;
            ResultSet rs = statement.executeQuery("SELECT `skin` FROM `selected_skins` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                select = rs.getString(1);
            }
            rs.close();

            if (select == null) {
                select = name;
            }

            ResultSet skin = statement.executeQuery("SELECT `value` FROM `skins_storage` WHERE `skin` = '" + select + "' LIMIT 1;");
            if (skin.next()) {
                value = skin.getString(1);
            }
            skin.close();

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public static void changeMoney(int playerID, int amount) {
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `balance` FROM `money` WHERE `id` = '" + playerID + "';");
            if (rs.next()) {
                connection.execute("UPDATE `money` SET `balance`=`balance`+'" + amount + "' WHERE `id`='" + playerID + "';");
            } else {
                connection.execute("INSERT INTO `money` (`id`, `balance`) VALUES ('" + playerID + "', " + amount + ");");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String getName(int playerID) {
        String name = "";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `player_name` FROM `identifier` WHERE `id` = '" + playerID + "';");
            if (rs.next()) {
                name = rs.getString("player_name");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public static Group getGroup(int playerID) {
        Group group = Group.DEFAULT;
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `group_id` FROM `players_groups` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                group = Group.getGroup(rs.getInt("group_id"));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return group;
    }

    public static String getDisplayName(int playerID) {
        String displayName = "§cError!";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT `player_name` FROM `identifier` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                displayName = rs.getString("player_name");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "§r" + getPrefix(playerID, getGroup(playerID)) + displayName;
    }

    public static List<Pair<Integer, Integer>> getSettings(int playerID) {
        List<Pair<Integer, Integer>> data = new ArrayList<>();
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `settings` WHERE `id` = '" + playerID + "';");
            while (rs.next()) {
                int type = rs.getInt("setting_id");
                int value = rs.getInt("setting_value");
                data.add(new Pair<>(type, value));
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void changeGold(int playerID, int gold) {
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `money` WHERE `id` = '" + playerID + "' LIMIT 1;");
            if (rs.next()) {
                connection.execute("UPDATE `money` SET `gold`=`gold`+'" + gold + "' WHERE `id`='" + playerID + "';");
            } else {
                connection.execute("INSERT INTO `money` (`id`, `gold`) VALUES ('" + playerID + "', " + gold + ");");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

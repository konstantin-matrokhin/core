package net.lastcraft.gamer;

import net.lastcraft.group.Group;
import net.lastcraft.group.SettingsType;

import java.util.List;

public interface IBaseGamer {
    int getLanguage();
    int getPlayerID();
    Group getGroup();
    String getPrefix();

    int getMoney();
    int getGold();
    int getExp();
    int getExpNextLevel();
    int getLevelNetwork(); //левел не по группе, а по EXP
    int getKeys();
    boolean getSetting(SettingsType type); //вернет значение настройки по ключу
    double getMultiple();
    List<Integer> getFriends();
    int getFriendsLimit();
    boolean isFriend(int playerID);

    void changeMoney(int money);
    void changeGold(int gold);
    boolean addExp(int exp); //true если будет lvlUp
    void changeKeys(int keys);
    void setSetting(SettingsType type, boolean value);
    void setLanguage(int value);
    void setPrefix(String prefix);
}

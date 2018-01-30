package net.lastcraft.gamer;

import net.lastcraft.gamer.data.*;
import net.lastcraft.group.Group;
import net.lastcraft.group.SettingsType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GamerBase implements IBaseGamer {

    private long start; //когда началась загрузка данных(для дебага)

    private String name;
    private Map<String, Section> sections;

    private BaseSection baseSection;
    private MoneySection moneySection;
    private NetworkingSection networkingSection;
    private SettingsSection settingsSection;
    private FriendsSection friendsSection;

    protected GamerBase(String name) {
        start = System.currentTimeMillis();
        this.name = name;
        sections = new HashMap<>();

        baseSection = new BaseSection(this);
        moneySection = new MoneySection(this);
        networkingSection = new NetworkingSection(this);
        settingsSection = new SettingsSection(this);
        friendsSection = new FriendsSection(this);

        for (Section section : sections.values()) {
            if (section.init()) continue;
            throw new IllegalArgumentException("кажется что-то пошло не так, секция - "
                    + section.getClass().getSimpleName() + ", ник игрока - " + name);
        }

        GamerAPI.getGamers().put(name.toLowerCase(), this);
    }

    public abstract GamerType getGamerType();

    public long getStart() {
        return start;
    }

    public Map<String, Section> getSections() {
        return sections;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getLanguage() {
        return settingsSection.getLang();
    }

    @Override
    public int getPlayerID() {
        return baseSection.getPlayerID();
    }

    @Override
    public Group getGroup() {
        return baseSection.getGroup();
    }

    @Override
    public String getPrefix() {
        return baseSection.getPrefix();
    }

    @Override
    public int getMoney() {
        return moneySection.getMoney();
    }

    @Override
    public int getGold() {
        return moneySection.getGold();
    }

    @Override
    public int getExp() {
        return networkingSection.getExp();
    }

    @Override
    public int getExpNextLevel() {
        return networkingSection.getExpNextLevel();
    }

    @Override
    public int getLevelNetwork() {
        return networkingSection.getLevel();
    }

    @Override
    public int getKeys() {
        return networkingSection.getKeys();
    }

    @Override
    public boolean getSetting(SettingsType type) {
        return settingsSection.getSetting(type);
    }

    @Override
    public double getMultiple() {
        return moneySection.getMultiple();
    }

    @Override
    public List<Integer> getFriends() {
        return friendsSection.getFriends();
    }

    @Override
    public int getFriendsLimit() {
        return friendsSection.getFriendsLimit();
    }

    @Override
    public boolean isFriend(int playerID) {
        return friendsSection.isFriend(playerID);
    }

    @Override
    public void changeMoney(int money) {
        moneySection.changeMoney(money);
    }

    @Override
    public void changeGold(int gold) {
        moneySection.changeGold(gold);
    }

    @Override
    public boolean addExp(int exp) {
        return networkingSection.addExp(exp);
    }

    @Override
    public void changeKeys(int keys) {
        networkingSection.changeKeys(keys);
    }

    @Override
    public void setSetting(SettingsType type, boolean value) {
        settingsSection.setSetting(type, value);
    }

    @Override
    public void setPrefix(String prefix) {
        baseSection.setPrefix(prefix);
    }

    @Override
    public void setLanguage(int lang) {
        settingsSection.setLang(lang);
    }
}

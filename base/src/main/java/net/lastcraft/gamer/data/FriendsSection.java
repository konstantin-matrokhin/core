package net.lastcraft.gamer.data;

import net.lastcraft.gamer.GamerBase;
import net.lastcraft.group.Group;
import net.lastcraft.sql.GlobalLoader;
import net.lastcraft.util.ConcurrentList;

import java.util.List;

public class FriendsSection extends Section {
    private List<Integer> friends;
    private int friendsLimit = 20;

    public FriendsSection(GamerBase baseGamer) {
        super(baseGamer);
    }

    @Override
    boolean loadData() {
        int playerID = getBaseGamer().getPlayerID();
        this.friends = GlobalLoader.getFriendsID(playerID);
        return true;
    }

    public int getFriendsLimit() {
        Group group = getBaseGamer().getGroup();
        if(group == Group.GOLD) friendsLimit = 30;
        if(group == Group.DIAMOND) friendsLimit = 40;
        if(group == Group.EMERALD) friendsLimit = 50;
        if(group.getLevel() >= 4) friendsLimit = 60;
        return friendsLimit;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public boolean isFriend(int playerID) {
        return friends.contains(playerID);
    }

    public void addFriend(int playerID) {
        if (!friends.contains(playerID)) {
            friends.add(playerID);
        }
    }

    public void removeFriend(int playerID) {
        friends.remove(playerID);
    }
}

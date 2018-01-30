package net.lastcraft.gamer.data;

import net.lastcraft.gamer.GamerBase;
import net.lastcraft.group.Group;
import net.lastcraft.sql.GlobalLoader;

public class BaseSection extends Section {

    private int playerID;
    private Group group;
    private String prefix;

    public BaseSection(GamerBase baseGamer) {
        super(baseGamer);
    }

    @Override
    protected boolean loadData() {
        String name = getBaseGamer().getName();
        playerID = GlobalLoader.getPlayerID(name);
        group = GlobalLoader.getGroup(playerID);
        prefix = GlobalLoader.getPrefix(playerID, group);

        return playerID != -1;
    }

    public int getPlayerID() {
        return playerID;
    }

    public Group getGroup() {
        return group;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

package net.lastcraft.gamer.data;

import net.lastcraft.gamer.GamerBase;
import net.lastcraft.sql.PlayerInfoLoader;

public class NetworkingSection extends Section {
    private int keys;
    private int exp;
    private int level;
    private int expNextLevel;

    public NetworkingSection(GamerBase baseGamer) {
        super(baseGamer);
    }

    @Override
    boolean loadData() {
        int playerID = getBaseGamer().getPlayerID();
        int[] network = PlayerInfoLoader.getData(playerID);

        for (int check : network) {
            if (check == -1) {
                return false;
            }
        }

        this.keys = network[0];
        this.exp = network[1];
        this.level = checkLVL(exp);

        return true;
    }

    public int getLevel() {
        return level;
    }

    public int getKeys() {
        return keys;
    }

    public int getExp() {
        return exp;
    }

    public int getExpNextLevel() {
        return expNextLevel;
    }

    public boolean addExp(int exp) {
        this.exp += exp;
        PlayerInfoLoader.updateData(getBaseGamer().getPlayerID(), "exp", exp);
        if (checkLVL(this.exp) > this.level) {
            this.level = checkLVL(this.exp);
            return true;
        }
        return false;
    }

    public void changeKeys(int keys) {
        this.keys += keys;
        PlayerInfoLoader.updateData(getBaseGamer().getPlayerID(), "keys", keys);
    }

    public int checkLVL(int exp) {
        for (int i = 1;;i++) {
            if (exp == 0) {
                expNextLevel = checkXPLVL(i);
                return 0;
            }
            if (checkXPLVL(i) == exp) {
                expNextLevel = checkXPLVL(i + 1);
                return i;
            }
            if (checkXPLVL(i) > exp) {
                expNextLevel = checkXPLVL(i) - exp;
                return i - 1;
            }
            exp -= checkXPLVL(i);
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int checkXPLVL(int level) {
        return 100 * 2 * level;
    }

    public static int getCurrentXPLVL(int exp) {
        for (int i = 0;;i++) {
            if (exp == 0) {
                return 0;
            }
            if (checkXPLVL(i) >= exp) {
                return exp;
            }
            exp -= checkXPLVL(i);
        }
    }
}

package net.lastcraft.gamer.data;

import net.lastcraft.gamer.GamerBase;
import net.lastcraft.sql.GlobalLoader;
import net.lastcraft.util.Pair;

public class MoneySection extends Section {
    private int gold;
    private int money;

    public MoneySection(GamerBase baseGamer) {
        super(baseGamer);
    }

    @Override
    protected boolean loadData() {
        Pair<Integer, Integer> pair = GlobalLoader.getPlayerMoney(getBaseGamer().getPlayerID());
        this.money = pair.getFirst();
        this.gold = pair.getSecond();

        return pair.getFirst() != -1 && pair.getSecond() != -1;
    }

    public int getGold() {
        return gold;
    }

    public int getMoney() {
        return money;
    }

    public double getMultiple() {
        int levelGroup = getBaseGamer().getGroup().getLevel();
        double multiple = 1.0;
        if (levelGroup >= 1) multiple = 1.5; //gold
        if (levelGroup >= 3) multiple = 1.75; //emerald
        if (levelGroup >= 4) multiple = 2.0; //magma
        return multiple;
    }

    public void changeMoney(int money) {
        this.money += money;
        GlobalLoader.changeMoney(getBaseGamer().getPlayerID(), money);
    }

    public void changeGold(int gold) {
        this.gold += gold;
        GlobalLoader.changeGold(getBaseGamer().getPlayerID(), gold);
    }
}

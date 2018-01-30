package net.lastcraft.gamer.data;

import net.lastcraft.gamer.GamerBase;

public abstract class Section {

    private GamerBase baseGamer;
    private boolean enabled;

    Section(GamerBase baseGamer) {
        this.baseGamer = baseGamer;
        String nameSection = getClass().getSimpleName();
        baseGamer.getSections().put(nameSection, this);
        enabled = this.loadData();
    }

    GamerBase getBaseGamer() {
        return baseGamer;
    }

    abstract boolean loadData();

    public boolean init() {
        return enabled;
    }
}
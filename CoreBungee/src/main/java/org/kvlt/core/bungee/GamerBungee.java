package org.kvlt.core.bungee;

import net.lastcraft.gamer.GamerAPI;
import net.lastcraft.gamer.GamerBase;

public class GamerBungee extends GamerBase {

    public static void ignoreOrCreate(String name) {
        GamerBungee gamer = (GamerBungee) GamerAPI.get(name);
        if (gamer == null) {
            new GamerBungee(name);
        }
    }

    public static void remove(String name) {
        GamerAPI.getGamers().remove(name);
    }

    public GamerBungee(String name) {
        super(name);
    }

}

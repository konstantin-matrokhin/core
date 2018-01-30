package net.lastcraft.gamer;

import net.lastcraft.group.Group;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GamerAPI {
    private final static Map<String, GamerBase> gamers;

    public static Map<String, GamerBase> getGamers() {
        return gamers;
    }

    public static GamerBase get(String name) {
        return getGamers().get(name.toLowerCase());
    }

    public static boolean contains(String name) {
        return getGamers().containsKey(name.toLowerCase());
    }

    public static boolean isPlayer(GamerBase base){
        return base.getGroup() == Group.DEFAULT;
    }

    public static boolean isAdmin(GamerBase base){
        return base.getGroup() == Group.ADMIN;
    }

    public static boolean isStaff(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.HELPER.getLevel();
    }

    public static boolean isDonater(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level > 0 && level < 8 && level != 5;
    }

    public static boolean isGold(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.GOLD.getLevel();
    }

    public static boolean isDiamond(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.DIAMOND.getLevel();
    }

    public static boolean isEmerald(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.EMERALD.getLevel();
    }

    public static boolean isMagma(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.MAGMA.getLevel();
    }

    public static boolean isShulker(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.SHULKER.getLevel();
    }

    public static boolean isYouTube(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.YOUTUBE.getLevel();
    }

    public static boolean isBuilder(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.BUILDER.getLevel();
    }

    public static boolean isJunior(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.JUNIOR.getLevel();
    }

    public static boolean isHelper(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.HELPER.getLevel();
    }

    public static boolean isModerator(GamerBase base) {
        int level = base.getGroup().getLevel();
        return level >= Group.MODERATOR.getLevel();
    }

    static {
        gamers = new ConcurrentHashMap<>();
    }
}

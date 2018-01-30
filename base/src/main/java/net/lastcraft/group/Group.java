package net.lastcraft.group;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Group {
    DEFAULT(0, "§7Игрок", "§7Default", "default", "§7", "§8 »§7", 0, 960),

    GOLD(100, "§eGold", "§eGold", "gold", "[§eGold§f] §e", "§8 »§7", 1, 950),
    DIAMOND(200, "§bDiamond", "§bDiamond", "diamond", "[§bDiamond§f] §b", "§8 »§7", 2, 900),
    EMERALD(300, "§aEmerald", "§aEmerald", "emerald", "[§aEmerald§f] §a", "§8 »§7", 3, 800),
    MAGMA(400, "§cMagma", "§cMagma", "magma", "[§cMagma§f] §c", "§8 »§7", 4, 700),

    SHULKER(450, "§7Shulker", "§7Shulker", "shulker", "[§7Shulker§f] §7", "§8 »§f", 5, 600),
    YOUTUBE(500, "§6YouTube", "§6YouTube", "youtube", "[§6YouTube§f] §6", "§8 »§f", 6, 500),
    JUNIOR(650, "§2Мл. Хелпер", "§2Junior", "junior", "[§2Junior§f] §2", "§8 »§f", 7, 400),

    BUILDER(600, "§3Строитель", "§3Builder", "builder", "[§3Builder§f] §3", "§8 »§f", 5, 300),
    HELPER(700, "§2Хелпер", "§2Helper", "helper", "[§2Helper§f] §2", "§8 »§f", 8, 200),
    MODERATOR(800, "§9Модератор", "§9Moderator", "moderator", "[§9Moder§f] §9", "§8 »§f", 9, 100),
    ADMIN(900, "§4Админ", "§4Admin", "administrator", "[§4Admin§f] §4", "§8 »§f", 10, 0);

    private static Map<Integer, Group> groups;

    public static Group getGroup(int groupID){
        return (groups.containsKey(groupID) ? groups.get(groupID) : groups.get(0));
    }

    public static Group getGroupByLevel(int level) {
        return groups.values().stream()
                .filter(g -> g.getLevel() == level)
                .findFirst()
                .orElse(Group.getGroup(0));
    }

    private final int id;
    private final String groupName;
    private final String prefix;
    private final String suffix;
    private final String name;
    private final String nameEn;
    private final int level;
    private final int scoreBoard;

    Group(int id, String name, String nameEn, String groupName, String prefix, String suffix, int level, int scoreBoard){
        this.id = id;
        this.name = name;
        this.nameEn = nameEn;
        this.groupName = groupName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.level = level;
        this.scoreBoard = scoreBoard;
    }

    public int getScoreBoard() {
        return scoreBoard;
    }

    public String getNameEn() {
        return nameEn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroupName(){
        return groupName;
    }

    public String getPrefix(){
        return prefix;
    }

    public String getSuffix(){
        return suffix;
    }

    public int getLevel(){
        return level;
    }

    @Override
    public String toString() {
        return "@Group[{" + id
                + "}, {" + name.replaceAll("§", "&")
                + "}, {" + groupName
                + "}, {" + prefix.replaceAll("§", "&")
                + "}, {" + suffix.replaceAll("§", "&")
                + "}, {" + level
                + "}]";
    }

    static {
        groups = new ConcurrentHashMap<>();
        for (Group gp : values()) {
            if (!groups.containsKey(gp.getId()))
                groups.put(gp.getId(), gp);
        }
    }
}

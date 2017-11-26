package org.kvlt.core.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Group {

    DEFAULT(0, "§7Игрок","default", "§7", "§8 »§7", 0),
    GOLD(100, "§eGold","gold", "[§eGold§f] §e", "§8 »§7", 1),
    DIAMOND(200, "§bDiamond","diamond", "[§bDiamond§f] §b", "§8 »§7", 2),
    EMERALD(300, "§aEmerald","emerald", "[§aEmerald§f] §a", "§8 »§7", 3),
    MAGMA(400, "§cMagma","magma", "[§cMagma§f] §c", "§8 »§7", 4),

    SHULKER(450, "§7Shulker","shulker", "[§7Shulker§f] §c", "§8 »§f", 5),
    YOUTUBE(500, "§6YouTube","yt", "[§6YouTube§f] §6", "§8 »§f", 6),
    JUNIOR(650, "§2Мл.Хелпер","junior", "[§2Junior§f] §9", "§8 »§f", 7),

    BUILDER(600, "§eСтроитель","builder", "[§3Builder§f] §6", "§8 »§f", 5),
    HELPER(700, "§2Хелпер","helper", "[§2Helper§f] §2", "§8 »§f", 8),
    MODERATOR(800, "§9Модератор","moder", "[§9Moder§f] §9", "§8 »§f", 9),
    ADMIN(900, "§4Админ","admin", "[§4Admin§f] §4", "§8 »§f", 10);

    private static Map<Integer, Group> groups = new ConcurrentHashMap<>();

    public static Group getGroup(int groupID){
        if (groups.containsKey(groupID)) {
            return groups.get(groupID);
        } else {
            return groups.get(0);
        }
    }

    public static Group getGroupByLevel(int level) {
        return groups.values().stream()
                .filter(g -> g.getLevel() == level)
                .findFirst()
                .orElse(Group.getGroup(0));

//        for (Group group : groups.values()){
//            if (group.getLevel() == level){
//                return group;
//            }
//        }
//        return Group.getGroup(0);
    }


    private int id;
    private String group;
    private String prefix;
    private String suffix;
    private String name;
    private int level;

    Group(int id, String name, String group, String prefix, String suffix, int level){
        this.id = id;
        this.name = name;
        this.group = group;
        this.prefix = prefix;
        this.suffix = suffix;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup(){
        return group;
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
                + "}, {" + group
                + "}, {" + prefix.replaceAll("§", "&")
                + "}, {" + suffix.replaceAll("§", "&")
                + "}, {" + level
                + "}]";
    }

    static {
        for (Group gp : values()) {
            if (!groups.containsKey(gp.getId()))
                groups.put(gp.getId(), gp);
        }
    }
}

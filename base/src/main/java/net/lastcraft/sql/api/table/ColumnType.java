package net.lastcraft.sql.api.table;

public enum ColumnType {
    INT("INT"),
    INT_11("INT(11)"),
    INT_2("INT(2)"),
    TINY_INT("TINYINT"),
    VARCHAR_16("VARCHAR(16)"),
    VARCHAR_32("VARCHAR(32)"),
    VARCHAR_48("VARCHAR(48)"),
    VARCHAR_64("VARCHAR(64)"),
    VARCHAR_128("VARCHAR(128)"),
    TEXT("TEXT(0)"),
    BIG_INT("BIGINT(18)"),
    BOOLEAN("BOOLEAN");

    private final String sql;

    ColumnType(String sql) {
        this.sql = sql;
    }

    String getSql() {
        return this.sql;
    }
}

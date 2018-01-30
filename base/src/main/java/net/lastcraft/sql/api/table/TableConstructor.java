package net.lastcraft.sql.api.table;

import net.lastcraft.sql.api.SQLConnection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableConstructor {
    private final String name;
    private final List<TableColumn> tableColumns;

    public TableConstructor(String name, TableColumn... tableColumns) {
        this.name = name;
        this.tableColumns = Arrays.asList(tableColumns);
    }

    @Override
    public String toString() {
        String columnSql = tableColumns.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        String primary = tableColumns.stream()
                .filter(TableColumn::isPrimaryKey)
                .map(TableColumn::getName)
                .collect(Collectors.joining(", "));
        if (primary != null && !primary.isEmpty()) {
            columnSql = columnSql + ", PRIMARY KEY (" + primary + ")";
        }

        return "CREATE TABLE IF NOT EXISTS `" + name + "` (" + columnSql + ") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;";
    }

    public void create(SQLConnection connector) {
        connector.execute(this.toString());
    }
}

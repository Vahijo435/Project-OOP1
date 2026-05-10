package bg.tu_varna.sit.f24621646.project_oop1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Database {
    private final Map<String, Table> tables = new HashMap<>();
    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public boolean hasTable(String tableName) {
        return tables.containsKey(tableName);
    }

    public Map<String, Table> getTables() {
        return tables;
    }
}

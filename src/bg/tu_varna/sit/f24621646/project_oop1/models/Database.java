package bg.tu_varna.sit.f24621646.project_oop1.models;

import java.util.Map;
import java.util.HashMap;

public class Database {
    private final Map<String, Table> tables = new HashMap<>();
    private final Map<String, String> tableFiles = new HashMap<>();
    public void addTable(Table table, String fileName) {
        tables.put(table.getName(), table);
        tableFiles.put(table.getName(), fileName);
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
    public String getFileForTable(String tableName) { 
        return tableFiles.get(tableName); 
    }

}

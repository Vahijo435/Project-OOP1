package bg.tu_varna.sit.f24621646.project_oop1.models;


import java.util.Map;
import java.util.HashMap;
/**
 * @author Vahan
 * Клас, представляващ структурата на цялата база от данни.
 * Съдържа колекция от всички текущо заредени в паметта таблици.
 *
 */
public class Database {
    private final Map<String, Table> tables = new HashMap<>();
    /**
     * Добавя таблица към базата данни.
     *
     */
    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }
    /**
     * Извлича таблица по нейното име.
     *
     */
    public Table getTable(String tableName) {
        return tables.get(tableName);
    }
    /**
     * Проверява дали дадена таблица съществува в базата.
     *
     */
    public boolean hasTable(String tableName) {
        return tables.containsKey(tableName);
    }
    /**
     * Връща карта (Map) с всички таблици в базата.
     *
     */
    public Map<String, Table> getTables() {
        return tables;
    }
}

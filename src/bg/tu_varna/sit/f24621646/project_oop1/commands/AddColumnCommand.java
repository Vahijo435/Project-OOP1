package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
/**
 * @author Vahan
 * Команда за добавяне на нова колона с определено име и тип към вече съществуваща таблица. Запълва съществуващите редове с NULL.
 *
 */
public class AddColumnCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "Няма заредена база от данни.";

        }

        if (args.length < 4) {
            return "Невалидни параметри. Употреба:"+getUsage();
        }

        String tableName = args[1];
        String columnName = args[2];
        String typeStr = args[3].toUpperCase();

        Database db = manager.getDatabase();
        if (!db.hasTable(tableName)) {
            return "Таблица '" + tableName + "' не съществува.";
        }
        Table table = db.getTable(tableName);
        try {
            DataType type = DataType.valueOf(typeStr);
            Column newColumn = new Column(columnName, type);
            table.addColumn(newColumn);
            return "Успешно добавена колона '" + columnName + "' от тип " + typeStr + " в таблица '" + tableName + "'.";
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Невалиден тип данни '" + typeStr + "'. Поддържани типове: INTEGER, DOUBLE, STRING.");
        }
    }
    @Override
   public String getUsage() {
        return "addcolumn <table name> <column name> <column type>";
    }
    public String getDetails(){
        return "Добавя нова колона в дадена таблица";
    }
}
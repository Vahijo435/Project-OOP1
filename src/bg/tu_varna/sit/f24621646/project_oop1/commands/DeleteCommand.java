package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

import java.util.List;
/**
 *  @author Vahan
 * Команда за изтриване на редове от дадена таблица,
 * чиято стойност в определена колона съвпада с подаденото условие.
 *
 */
public class DeleteCommand implements Command {
    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) {
            return "Няма отворена база данни.";
        }

        if (args.length < 4) {
            return "Употреба:"+getUsage();
        }

        String tableName = args[1];
        Database db = manager.getDatabase();
        if (!db.hasTable(tableName)) {
            return "Таблица '" + tableName + "' не съществува.";
        }
        Table table = db.getTable(tableName);
        int searchColIndex = table.parseColumnIndex(args[2]);


        String searchVal = args[3];
        List<Row> rowsToDelete = table.findRowsByColumnValue(searchColIndex, searchVal);

        for (Row row : rowsToDelete) {
            table.removeRow(row);
        }

        return "Успешно изтрити " + rowsToDelete.size() + " реда от таблица '" + tableName + "'.";
    }

    @Override
    public String getUsage() {
        return "delete <table name> <search column n> <search value>";
    }
    public String getDetails(){
        return "Изтрива всички редове, отговарящи на критерия";
    }
}

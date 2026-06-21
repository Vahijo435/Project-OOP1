package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

import java.util.List;

/**
 * @author Vahan
 * Команда за промяна на стойности в съществуващи редове на дадена таблица спрямо зададено условие за търсене.
 *
 */
public class UpdateCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) {
            return "Няма отворена база данни.";

        }

        if (args.length < 6) {
            return "Употреба: "+getUsage();

        }
        String tableName=args[1];
        Database db = manager.getDatabase();
        if (!db.hasTable(tableName)) {
            return "Таблицата '" + tableName + "' не съществува.";
        }
        Table table = db.getTable(tableName);
        int searchCol = table.parseColumnIndex(args[2]);
        int targetCol = table.parseColumnIndex(args[4]);
        String searchVal = args[3];
        String targetVal = args[5];

        DataType targetType = table.getColumns().get(targetCol).getType();
        Value newTargetValue = targetType.parse(targetVal);
        List<Row> rowsToUpdate = table.findRowsByColumnValue(searchCol, searchVal);

        for (Row row : rowsToUpdate) {
            row.setValue(targetCol, newTargetValue);
        }
        return "Успешно обновени клетки в " + rowsToUpdate.size()  + " реда.";
    }

    @Override
    public String getUsage() {
        return "update <table name> <search column n> <search value> <target column n> <target value>";
    }
    public String getDetails() {
      return  "Променя стойности в клетки по дадено условие";
    }
}

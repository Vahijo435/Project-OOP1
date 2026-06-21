package bg.tu_varna.sit.f24621646.project_oop1.commands;

import java.util.ArrayList;
import java.util.List;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
/**
 * @author Vahan
 * Команда за добавяне на нов ред в дадена таблица.
 * Валидира броя и типа на подадените стойности преди добавяне.
 *
 */
public class InsertCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "В момента няма отворена база данни.";

        }

        if (args.length < 2) {
            return "Липсва име на таблица. Употреба: "+getUsage();

        }

        String tableName = args[1];
        Database db = manager.getDatabase();

        if (!db.hasTable(tableName)) {
            return "Таблица '" + tableName + "' не съществува.";
        }

        Table table = db.getTable(tableName);
        List<Column> columns = table.getColumns();

        if (columns.isEmpty()) {
            return "Таблица '" + tableName + "' няма дефинирани колони. Използвайте import или addcolumn първо.";
        }

        int expectedValuesCount = columns.size();
        int providedValuesCount = args.length - 2;

        if (providedValuesCount != expectedValuesCount) {
            return "Очакваха се " + expectedValuesCount + " стойности, но бяха получени " + providedValuesCount + ".";
        }

        List<Value> values = new ArrayList<>();
        for (int i = 0; i < expectedValuesCount; i++) {
            String rawValue = args[i+2];
            Column col = columns.get(i);

            try {
            values.add(col.getType().parse(rawValue));
            } catch (IllegalArgumentException e) {
                throw new DatabaseException("Невалидни стойности '" + rawValue + "' за колона '" + col.getName() + "' от тип " + col.getType());
            }
        }

        table.addRow(new Row(values));
        return "Успешно вмъкване на 1 ред в таблица '" + tableName + "'.";
    }

    @Override 
    public String getUsage() {
        return "insert <table name> <column 1> ... <column n>";
    }
    public String getDetails(){
        return "Вмъква нов ред с посочените стойности в таблицата";
    }
    

}
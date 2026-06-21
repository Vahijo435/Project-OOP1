package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vahan
 * Команда за извършване на релационна операция Inner Join.
 * Обединява две таблици спрямо съвпадения в зададени колони и генерира нова таблица.
 *
 */
public class InnerJoinCommand implements Command {
    @Override
    public String execute(String[] args) {
            DatabaseManager manager = DatabaseManager.getInstance();
            if (!manager.isDatabaseOpen()) return "Няма отворена база данни.";
            if (args.length < 5) return "Употреба: " + getUsage();
            Database db = manager.getDatabase();
            String t1Name = args[1];
            String t2Name = args[3];
            if (!db.hasTable(t1Name)) return "Таблица '" + t1Name + "' не съществува.";
            if (!db.hasTable(t2Name)) return "Таблица '" + t2Name + "' не съществува.";
            Table t1 = db.getTable(t1Name);
            Table t2 = db.getTable(t2Name);
            int col1 = t1.parseColumnIndex(args[2]);
            int col2 = t2.parseColumnIndex(args[4]);
            String newName = t1.getName() + "_" + t2.getName() + "_join";
            if (manager.getDatabase().hasTable(newName)) {
                newName = newName+"_"+System.currentTimeMillis();
            }

            Table newTable = new Table(newName, newName + ".txt");

            for (Column c : t1.getColumns()) newTable.addColumn(new Column(t1.getName() + "_" + c.getName(), c.getType()));
            for (Column c : t2.getColumns()) newTable.addColumn(new Column(t2.getName() + "_" + c.getName(), c.getType()));

        for (Row r1 : t1.getRows()) {
            Value val1 = r1.getValue(col1);
            List<Row> matchingRowsInT2 = t2.findRowsByValue(col2, val1);

            for (Row r2 : matchingRowsInT2) {
                List<Value> combinedValues = new ArrayList<>(r1.getValues());
                combinedValues.addAll(r2.getValues());
                newTable.addRow(new Row(combinedValues));
            }
        }

        manager.getDatabase().addTable(newTable);
        if (newTable.getRows().isEmpty()) {
            return "Inner Join приключи без съвпадения. Създадена е празна таблица с име: " + newName;
        } else {
            return "Успешен Inner Join! Създадена е нова таблица с идентификатор: " + newName;
        }
        }

    @Override
    public String getUsage() {
        return "innerjoin <table 1> <column n1> <table 2> <column n2>";
    }
    @Override
    public String getDetails() {
        return "Извършва операцията Inner Join над две таблици спрямо зададените колони";
    }

}


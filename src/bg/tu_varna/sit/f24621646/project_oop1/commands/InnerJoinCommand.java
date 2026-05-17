package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            Table t1 = manager.getDatabase().getTable(args[1]);
            Table t2 = manager.getDatabase().getTable(args[3]);

            if (t1 == null) return "Таблица '" + args[1] + "' не съществува.";
            if (t2 == null) return "Таблица '" + args[3] + "' не съществува.";

        int col1, col2;
        try {
            col1 = Integer.parseInt(args[2])-1;
            col2 = Integer.parseInt(args[4])-1;
        } catch (NumberFormatException e) {
            throw new DatabaseException("Индексите на колоните трябва да бъдат цели числа."+ e.getMessage());
        }
        if (col1 < 0 || col1 >= t1.getColumns().size()) {
            return "Невалиден индекс за първата таблица '" + t1.getName() + "'.";
        }
        if (col2 < 0 || col2 >= t2.getColumns().size()) {
            return "Невалиден индекс за втората таблица '" + t2.getName() + "'.";
        }
            String newName = t1.getName() + "_" + t2.getName() + "_join";
            if (manager.getDatabase().hasTable(newName)) {
                newName = newName+"_"+System.currentTimeMillis();
            }

            Table newTable = new Table(newName, newName + ".txt");

            for (Column c : t1.getColumns()) newTable.addColumn(new Column(t1.getName() + "_" + c.getName(), c.getType()));
            for (Column c : t2.getColumns()) newTable.addColumn(new Column(t2.getName() + "_" + c.getName(), c.getType()));

        for (Row r1 : t1.getRows()) {
            Object val1 = r1.getValue(col1).getRawValue();

            for (Row r2 : t2.getRows()) {
                Object val2 = r2.getValue(col2).getRawValue();

                if (Objects.equals(val1, val2)) {
                    List<Value> combinedValues = new ArrayList<>(r1.getValues());
                    combinedValues.addAll(r2.getValues());
                    newTable.addRow(new Row(combinedValues));
                }
            }
        }

            manager.getDatabase().addTable(newTable);
            return "Успешен Inner Join! Създадена е нова таблица с идентификатор: " + newName;
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


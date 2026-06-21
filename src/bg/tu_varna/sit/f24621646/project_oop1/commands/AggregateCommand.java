package bg.tu_varna.sit.f24621646.project_oop1.commands;


import bg.tu_varna.sit.f24621646.project_oop1.contracts.AggregationStrategy;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
import bg.tu_varna.sit.f24621646.project_oop1.commands.calculations.MaximumStrategy;
import bg.tu_varna.sit.f24621646.project_oop1.commands.calculations.MinimumStrategy;
import bg.tu_varna.sit.f24621646.project_oop1.commands.calculations.ProductStrategy;
import bg.tu_varna.sit.f24621646.project_oop1.commands.calculations.SumStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vahan
 * Команда за извършване на математически операции (сумиране, произведение, намиране на максимум или минимум).
 * Оперира върху числовите стойности на дадена колона за редовете, отговарящи на условие.
 *
 */
public class AggregateCommand implements Command {
    private final Map<String, AggregationStrategy> strategies = new HashMap<>();

    public AggregateCommand() {
        strategies.put("sum",new SumStrategy());
        strategies.put("product", new ProductStrategy());
        strategies.put("maximum",new MaximumStrategy());
        strategies.put("minimum",new MinimumStrategy());
    }

    @Override
    public String execute(String[] args) {

        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) return "Няма отворена база данни.";
        if (args.length < 6) return "Употреба: " + getUsage();
        String tableName=args[1];
        Database db = manager.getDatabase();
        if (!db.hasTable(tableName)) {
            return "Таблица '" + tableName + "' не съществува.";
        }

        Table table = db.getTable(tableName);

        int searchCol = table.parseColumnIndex(args[2]);
        int targetCol = table.parseColumnIndex(args[4]);

        String searchVal = args[3];
        String operation = args[5].toLowerCase();

        if (!strategies.containsKey(operation)) {
            return "Невалидна операция. Използвайте: " + String.join(", ", strategies.keySet());
        }


        DataType type = table.getColumns().get(targetCol).getType();
        if (!type.isNumeric()) {
            return "Целевата колона трябва да е числова.";
        }
        List<Row> matchingRows = table.findRowsByColumnValue(searchCol, searchVal);

        if (matchingRows.isEmpty()) {
            return "Няма намерени записи, отговарящи на условието за търсене.";
        }

        List<Double> numericValues = new ArrayList<>();
        for (Row row : matchingRows) {
            Value val = row.getValue(targetCol);
            if (val.isNull()) {
                continue;
            }
            numericValues.add(val.getAsDouble());
        }

        if (numericValues.isEmpty()) {
            return "Няма намерени числови данни за обработка (възможно е целевите клетки да са NULL).";
        }

        double result = strategies.get(operation).calculate(numericValues);
        return "Резултат (" + operation + "): " + result;
    }

    @Override
    public String getUsage() {
        return "aggregate <table name> <search col n> <search val> <target col n> <operation>";
    }

    @Override
    public String getDetails() {
        return "Извършва математическа операция (sum, product, maximum, minimum)";
    }
}

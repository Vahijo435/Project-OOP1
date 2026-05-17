package bg.tu_varna.sit.f24621646.project_oop1.commands;


import bg.tu_varna.sit.f24621646.project_oop1.contracts.AggregationStrategy;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
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

        Table table = manager.getDatabase().getTable(args[1]);
        if (table == null) return "Таблица '" + args[1] + "' не съществува.";


        int searchCol;
        int targetCol;
        String searchVal = args[3];
        String operation = args[5].toLowerCase();
        try {
            searchCol = Integer.parseInt(args[2])-1;
            targetCol = Integer.parseInt(args[4])-1;
        } catch (NumberFormatException e) {
            throw new DatabaseException("Номерата на колоните трябва да са цели числа.");
        }
        AggregationStrategy strategy = strategies.get(operation);
        if (strategy == null) {
            return "Невалидна операция. Използвайте: " + String.join(", ", strategies.keySet());
        }

        int colCount = table.getColumns().size();
        if (searchCol < 0 || searchCol >= colCount) return "Невалиден индекс за търсеща колона.";
        if (targetCol < 0 || targetCol >= colCount) return "Невалиден индекс за целева колона.";



        DataType type = table.getColumns().get(targetCol).getType();
        if (type != DataType.INTEGER && type != DataType.DOUBLE) {
            return "Грешка: Целевата колона трябва да е числова (INTEGER или DOUBLE).";
        }
        List<Row> matchingRows = table.findRowsByColumnValue(searchCol, searchVal);

        if (matchingRows.isEmpty()) {
            return "Няма намерени записи, отговарящи на условието за търсене.";
        }

        List<Double> numericValues = new ArrayList<>();
        for (Row row : matchingRows) {
            Value val = row.getValue(targetCol);
            if (val.getRawValue() == null) continue;

            if (val.getType() == DataType.INTEGER) {
                numericValues.add(((Integer) val.getRawValue()).doubleValue());
            } else if (val.getType() == DataType.DOUBLE) {
                numericValues.add((Double) val.getRawValue());
            }
        }

        if (numericValues.isEmpty()) {
            return "Няма намерени числови данни за обработка (възможно е целевите клетки да са NULL).";
        }

        double result = strategy.calculate(numericValues);
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

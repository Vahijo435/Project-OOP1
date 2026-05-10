package bg.tu_varna.sit.f24621646.project_oop1.commands;
import java.util.List;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

public class DescribeCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "В момента няма отворена база от данни.";

        }

        if (args.length < 2) {
            return "Липсва име на таблица. Употреба: "+getUsage();

        }

        String tableName = args[1];

        if (!manager.getDatabase().hasTable(tableName)) {
            return "Таблицата '" + tableName + "' не същесвува.";

        }

        Table table = manager.getDatabase().getTable(tableName);
        List<Column> columns = table.getColumns();

        if (columns.isEmpty()) {
            return "Таблица '" + tableName + "' няма дефинирани колони.";

        }

        StringBuilder sb = new StringBuilder();
        sb.append("Структура на таблица '").append(tableName).append("':\n");
        
        for (Column col : columns) {
            sb.append("- ").append(col.getName()).append(" : ").append(col.getType().toString()).append("\n");
        }

        return sb.toString().trim();
    }

    @Override 
    public String getUsage() {
        return "describe <таблица>";
    }
    public String getDetails() {
        return "Показва информация за типовете колони в дадена таблица";
    }



}
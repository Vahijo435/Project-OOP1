package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import java.util.ArrayList;
import java.util.List;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

public class InsertCommand implements Command {
    private String output;

    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            this.output = "Error: No database is currently open.";
            return;
        }

        if (args.length < 2) {
            this.output = "Missing table name. Usage: insert <table name> <value1> <value2> ... <valueN>";
            return;
        }

        String tableName = args[1];
        Database db = manager.getDatabase();

        if (!db.hasTable(tableName)) {
            this.output = "Error: Table '" + tableName + "' does not exist.";
            return;
        }

        Table table = db.getTable(tableName);
        List<Column> columns = table.getColumns();

        if (columns.isEmpty()) {
            this.output = "Error: Table '" + tableName + "' has no columns defined. Use import or addcolumn first.";
            return;
        }

        int expectedValuesCount = columns.size();
        int providedValuesCount = args.length - 2;

        if (providedValuesCount != expectedValuesCount) {
            this.output = "Error: Expected " + expectedValuesCount + " values, but got " + providedValuesCount + ".";
            return;
        }

        List<Object> values = new ArrayList<>();
        for (int i = 0; i < expectedValuesCount; i++) {
            String rawValue = args[i+2];
            Column col = columns.get(i);
            
            try {
            values.add(col.getType().parse(rawValue));
            } catch (IllegalArgumentException e) {
                throw new DatabaseException("Error: Invalid value '" + rawValue + "' for column '" + col.getName() + "' of type " + col.getType());
            }
        }

        table.addRow(new Row(values));
        this.output = "Successfully inserted 1 row into table '" + tableName + "'.";
    }

    @Override 
    public String getDetails() {
        return "insert <table name> <value1> <value2> ... <valueN> - Inserts a new row with the specified values into the table";
    }
    

    @Override 
    public String toString() { 
        return output; 
    }
}
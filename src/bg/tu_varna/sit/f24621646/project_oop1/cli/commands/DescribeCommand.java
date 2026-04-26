package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;
import java.util.List;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

public class DescribeCommand implements Command {
    private String output;

    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            this.output = "No database is currently open.";
            return;
        }

        if (args.length < 2) {
            this.output = "Missing table name. Usage: describe <name>";
            return;
        }

        String tableName = args[1];

        if (!manager.getDatabase().hasTable(tableName)) {
            this.output = "Table '" + tableName + "' does not exist.";
            return;
        }

        Table table = manager.getDatabase().getTable(tableName);
        List<Column> columns = table.getColumns();

        if (columns.isEmpty()) {
            this.output = "Table '" + tableName + "' has no columns defined.";
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Structure of table '").append(tableName).append("':\n");
        
        for (Column col : columns) {
            sb.append("- ").append(col.getName()).append(" : ").append(col.getType().toString()).append("\n");
        }

        this.output = sb.toString().trim();
    }

    @Override 
    public String getDetails() {
        return "describe <name> - Shows information about the column types of a given table";
    }

    
    @Override 
    public String toString() { 
        return output; 
    }
}
package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import java.util.List;
import java.util.Scanner;

import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;

public class PrintCommand implements Command {
    private String output;
    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            this.output = "Error: No database is currently open.";
            return;
        }
        if (args.length < 2) {
            this.output = "Missing table name. Usage: print <table name>";
            return;
        }

        Table table = manager.getDatabase().getTable(args[1]);
        if (table == null) {
            this.output = "Error: Table '" + args[1] + "' not found.";
            return;
        }

        List<Column> columns = table.getColumns();
        List<Row> rows = table.getRows();

        if (columns.isEmpty()) {
            this.output = "Table '" + table.getName() + "' has no columns defined.";
            return;
        }
        if (rows.isEmpty()) {
            this.output = "Table '" + table.getName() + "' is empty.";
            return;
        }

        int[] colWidths = new int[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            colWidths[i] = columns.get(i).getName().length();
        }

        for (Row row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                String val = row.getDisplayValue(i);
                if (val.length() > colWidths[i]) {
                    colWidths[i] = val.length();
                }
            }
        }


            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < columns.size(); i++) {
                sb.append(String.format("%-" + colWidths[i] + "s", columns.get(i).getName()));
                if (i < columns.size() - 1) sb.append(" | ");
            }
            sb.append("\n");

            for (int i = 0; i < columns.size(); i++) {
                sb.repeat("-", colWidths[i]);
                if (i < columns.size() - 1) sb.append("-|-");
            }
            sb.append("\n");

            for (Row row : rows) {
                for (int i = 0; i < columns.size(); i++) {
                    sb.append(String.format("%-" + colWidths[i] + "s", row.getDisplayValue(i)));
                    if (i < columns.size() - 1) sb.append(" | ");
                }
                sb.append("\n");
            }


        this.output = sb.toString().trim();

    }
    @Override
    public String getDetails() {
        return "print <table name> - Displays all rows of a table";
    }
    @Override
    public String toString() {
        return output;
    }
}
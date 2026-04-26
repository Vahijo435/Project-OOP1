package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;
import java.util.Set;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class ShowTablesCommand implements Command {
    private String output;


    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            this.output = "No database is currently open.";
            return;
        }

        Set<String> tableNames = manager.getDatabase().getTables().keySet();

        if (tableNames.isEmpty()) {
            this.output = "No tables have been imported yet.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String name : tableNames) {
                sb.append(name).append("\n");
            }
            this.output = sb.toString().trim();
        }
    }


    @Override
    public String getDetails() {
        return "showtables - displays all imported tables";
    }
    @Override 
    public String toString() { 
        return output; 
    }
}

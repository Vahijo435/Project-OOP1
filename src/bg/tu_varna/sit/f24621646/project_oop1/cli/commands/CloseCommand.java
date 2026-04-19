package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class CloseCommand implements Command {
    private String output;

    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            this.output = "No database is currently open.";
            return;
        }
        manager.closeDatabase();
        this.output = "Successfully closed the database. Unsaved changes are lost.";
    }

    @Override public String getUsage() { 
        return "close"; 
    }
    @Override public String getDescription() { 
        return "closes currently opened file"; 
    }
    @Override public String toString() { 
        return output; 
    }
}

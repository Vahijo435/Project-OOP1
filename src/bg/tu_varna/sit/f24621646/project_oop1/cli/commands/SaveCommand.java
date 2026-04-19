package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;


public class SaveCommand implements Command {
    private String output;


    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            this.output = "Error: No database is currently open.";
            return;
        }

        Database db = manager.getDatabase();
        String catalogPath = manager.getCurrentCatalogFilePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(catalogPath))) {
            for (Map.Entry<String, Table> entry : db.getTables().entrySet()) {
                String tableName = entry.getKey();
                String tableFileName = db.getFileForTable(tableName);

                bw.write(tableName + "," + tableFileName);
                bw.newLine();
                
            }
            for (Map.Entry<String, Table> entry : db.getTables().entrySet()) {
                Table table = entry.getValue();
                String tableFileName = db.getFileForTable(table.getName());
                
                manager.saveTableToFile(table, tableFileName);
            }
            this.output = "Successfully saved " + catalogPath;
        } catch (IOException e) {
            throw new DatabaseException("Error saving database: " + e.getMessage());
        }
    }

    @Override 
    public String getUsage() { 
        return "save"; 
    }
    @Override 
    public String getDescription() { 
        return "saves the currently open file"; 
    }
    @Override 
    public String toString() { 
        return output; 
    }
}
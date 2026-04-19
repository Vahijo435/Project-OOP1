package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import java.io.File;

import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class ImportCommand implements Command {
    private String output;


public void execute(String[] args) {
    DatabaseManager manager = DatabaseManager.getInstance();

    if (!manager.isDatabaseOpen()) {
        this.output = "You must open a database first before importing.";
        return;
    }
    if (args.length < 2) {
        output = "Missing file name. Usage: import <file name>";
        return;
    }

    String inputPath = args[1]; 
    
    String fileName = inputPath;
    if (!fileName.endsWith(".txt")) {
        fileName = fileName + ".txt";
    }
    
    File file = new File(fileName);
    String tableName = file.getName().replace(".txt", "");

    if (manager.getDatabase().hasTable(tableName)) {
        this.output = "Table '" + tableName + "' already exists.";
        return;
    }

    manager.loadTableFromFile(fileName, tableName);

    this.output = "Successfully imported table '" + tableName + "' from " + fileName;
}
            

    @Override 
    public String getUsage() { 
        return "import <file name>"; 
    }
    @Override 
    public String getDescription() { 
        return "Imports a table from a file"; 
    }
    @Override 
    public String toString() { 
        return output; 
    }
}

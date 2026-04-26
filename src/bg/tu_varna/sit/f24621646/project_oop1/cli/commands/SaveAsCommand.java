package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class SaveAsCommand implements Command {
    private String output;


    @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) {
            this.output = "No database is currently open.";
            return;
        }
        if (args.length < 3) {
            this.output = "Missing file path. Usage: save as <file>";
            return;
        }
  
        String newPath = args[2]+".txt";
        
        manager.setCurrentCatalogFilePath(newPath);
        
        new SaveCommand().execute(new String[]{"save"});
        
        this.output = "Successfully saved as " + newPath;
    }

    @Override 
    public String getDetails() {
        return "save as <file> - saves the currently open file in <file>";
    }

    @Override 
    public String toString() { 
        return output; 

    }
}
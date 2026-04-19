package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;


public class OpenCommand implements Command {
    private String output;

     @Override
    public void execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (args.length == 1) {
            this.output = "Missing file path. Usage: open <file>";
            return;
        }

        String filePath = args[1] + ".txt";
        File file = new File(filePath);

        try {
            boolean createdNew = file.createNewFile();
            
            manager.openNewDatabase(filePath);

            if (createdNew) {
                this.output = "Successfully created and opened new file " + file.getName();
            } else {
                this.output = "Successfully opened: " + file.getName();

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty()) continue;

                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            String fileName = parts[1].trim();
                            new ImportCommand().execute(new String[]{"import",fileName});

                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new DatabaseException("Error opening file: " + e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "open <file>";
    }

    @Override
    public String getDescription() {
        return "opens <file>";
    }

    @Override
    public String toString() {
        return output;
    }
}

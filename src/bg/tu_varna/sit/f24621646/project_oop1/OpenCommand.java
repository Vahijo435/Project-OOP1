package bg.tu_varna.sit.f24621646.project_oop1;

import java.io.File;

public class OpenCommand implements Command{

   @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing file path. Usage: open <file>");
            return;
        }
        
        String filePath = args[0];
        File file = new File(filePath);
        
        try {

            if (file.createNewFile()) {
                System.out.println("Successfully created new file " + file.getName());
            } else { 
                System.out.println("Successfully opened: " + file.getName());
            }
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
        }


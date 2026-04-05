package bg.tu_varna.sit.f24621646.project_oop1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenCommand implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            OutputManager.getWriter().write("Missing file path. Usage: open <file>\n");
            return;
        }

        String filePath = args[1];
        File file = new File(filePath);
        BufferedReader br = null;

        try {
            if (file.createNewFile()) {
                OutputManager.getWriter().write("Successfully created new file " + file.getName() + "\n");
            } 
            br = new BufferedReader(new FileReader(file));
            OutputManager.getWriter().write("Successfully opened: " + file.getName() + "\n");
            
        } catch (IOException e) {
            throw new RuntimeException("Error opening file: " + e.getMessage());
        }
        finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                    throw new RuntimeException("Unable to close BufferedReader: "+ e.getMessage());
                }
            }
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
}

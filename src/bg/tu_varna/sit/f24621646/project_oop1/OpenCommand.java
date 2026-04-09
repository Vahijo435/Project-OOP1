package bg.tu_varna.sit.f24621646.project_oop1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenCommand implements Command {
    private String output;
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            this.output = "Missing file path. Usage: open <file>"; 
            return;
        }

        String filePath = args[1];
        File file = new File(filePath);
        BufferedReader br = null;

        try {
            StringBuilder sb = new StringBuilder();
            if (file.createNewFile()) {
                sb.append("Successfully created new file ").append(file.getName()).append("\n");            } 
                br = new BufferedReader(new FileReader(file));
                sb.append("Successfully opened: ").append(file.getName());  
                this.output = sb.toString();          
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
    @Override
    public String toString() {
        return output;
    }
}

package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
private final Map<String, Command> commands=new HashMap<>();
    public CLI() {
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("open", new OpenCommand());
    }

    public void start(){
        Scanner scanner=new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Database");
        while(isRunning){
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                String[] sp = line.split("\\s+");
                Command cmd = commands.get(sp[0].toLowerCase());
                String[] args=new String[sp.length-1];
                for(int i=1;i<sp.length;i++) args[i-1]=sp[i];
                if(cmd != null){
                    if (sp[0].equalsIgnoreCase("exit")) isRunning = false; 
                    cmd.execute(args);
                } else System.out.println("Unknown command: " + sp[0] + ". Type help to see for available commands.");
            }
        }
        scanner.close();
    }
}
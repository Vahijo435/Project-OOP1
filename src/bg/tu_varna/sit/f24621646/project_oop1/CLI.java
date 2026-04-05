package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
private final Map<String, Command> commands=new HashMap<>();
    public CLI() {
        commands.put("exit", new ExitCommand());
        commands.put("open", new OpenCommand());
        

        Command help = new HelpCommand(commands.values());
        commands.put(help.getUsage(), help);
    }

    public void start(){
        Scanner scanner=new Scanner(System.in);
        boolean isRunning = true;
        OutputManager.getWriter().write("Database\n");
        while(isRunning){
            OutputManager.getWriter().write("> ");
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                String[] args = line.split("\\s+");
                Command cmd = commands.get(args[0].toLowerCase());
                if(cmd != null){
                    if (args[0].equalsIgnoreCase("exit")) isRunning = false; 
                    cmd.execute(args);
                } else OutputManager.getWriter().write("Unknown command: " + args[0] + ". Type help to see for available commands.");
            }
        }
        scanner.close();
    }
}
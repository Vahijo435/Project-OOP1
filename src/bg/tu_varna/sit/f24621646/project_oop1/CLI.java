package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Map;
import java.util.HashMap;


public class CLI {
private final Map<String, Command> commands=new HashMap<>();
    public CLI() {
        commands.put("exit", new ExitCommand());
        commands.put("open", new OpenCommand());
        

        Command help = new HelpCommand(commands.values());
        commands.put(help.getUsage(), help);
    }

    public String process(String line){
        String[] args = line.split("\\s+");
        Command cmd = commands.get(args[0].toLowerCase());
                if(cmd != null){
                    cmd.execute(args);
                    return cmd.toString();
                } else  return "Unknown command: " + args[0] + ". Type help to see for available commands.";
            }
        }

    

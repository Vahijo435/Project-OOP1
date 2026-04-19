package bg.tu_varna.sit.f24621646.project_oop1.cli;

import java.util.Map;

import bg.tu_varna.sit.f24621646.project_oop1.cli.commands.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class CLI {
private final Map<String, Command> commands=new HashMap<>();

    public void reg(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }
    public Collection<Command> getCommands() {
        return commands.values();
    }
    private String[] parseArgs(String line) {
    List<String> args = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    boolean isInQuotes = false;
    
    for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        
        if (c == '"') {
            isInQuotes = !isInQuotes;
        } else if (Character.isWhitespace(c) && !isInQuotes) {
            if (cur.length() > 0) {
                args.add(cur.toString());
                cur = new StringBuilder();
            }
        } else {
            cur.append(c);
        }
    }
   if (cur.length() > 0) {
        args.add(cur.toString());
    }
    return args.toArray(new String[0]);
}
    public String process(String line){
        String[] args = parseArgs(line);
        String commandName=args[0].toLowerCase();
        if (commandName.equals("save") && args.length > 1 && args[1].equalsIgnoreCase("as")) commandName = "save as";
        Command cmd = commands.get(commandName);

        if(cmd != null){
            cmd.execute(args);
            return cmd.toString();
        } else return "Unknown command: " + commandName + ". Type help to see for available commands.";
            }
        }

    

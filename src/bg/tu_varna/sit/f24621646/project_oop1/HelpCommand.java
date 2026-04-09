package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Collection;

public class HelpCommand implements Command {
    private final Collection<Command> availableCommands;
        private String output;
    public HelpCommand(Collection<Command> commands) {
        this.availableCommands = commands;
    }
    @Override
    public void execute(String[] args) {
                StringBuilder sb = new StringBuilder();
        sb.append("The following commands are supported:\n");

        for (Command cmd : availableCommands) {
            sb.append(cmd.getUsage())
              .append(" - ")
              .append(cmd.getDescription())
              .append("\n");
        }

        this.output = sb.toString(); 
     }

 
    @Override
    public String getUsage() { 
        return "help"; 
    }

    @Override
    public String getDescription() { 
        return "Shows this help message"; 
    }
    @Override
    public String toString() {
        return output;
    }
    

}
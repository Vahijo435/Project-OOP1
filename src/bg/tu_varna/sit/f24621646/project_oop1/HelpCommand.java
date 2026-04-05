package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Collection;

public class HelpCommand implements Command {
    private final Collection<Command> availableCommands;
    public HelpCommand(Collection<Command> commands) {
        this.availableCommands = commands;
    }
    @Override
    public void execute(String[] args) {
        OutputManager.getWriter().write(printCommands());
    }
    public String printCommands(){
        StringBuilder sb = new StringBuilder();
        sb.append("The following commands are supported:\n");

        for (Command cmd : availableCommands) {
            sb.append(cmd.getUsage())
              .append(" - ")
              .append(cmd.getDescription())
              .append("\n");
        }

        return sb.toString();
    } 
 
    @Override
    public String getUsage() { 
        return "help"; 
    }

    @Override
    public String getDescription() { 
        return "Shows this help message"; 
    }

}
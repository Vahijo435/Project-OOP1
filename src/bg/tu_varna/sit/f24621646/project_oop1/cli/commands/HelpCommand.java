package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;


import bg.tu_varna.sit.f24621646.project_oop1.cli.CLI;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;


public class HelpCommand implements Command {
    private final CLI cli;
    private String output;
    public HelpCommand(CLI cli) {
        this.cli = cli;

    }
    @Override
    public void execute(String[] args) {
                StringBuilder sb = new StringBuilder();
        sb.append("The following commands are supported:\n");

        for (Command cmd : cli.getCommands()) {
            sb.append(cmd.getDetails()).append("\n");
        }
        this.output = sb.toString();
    }

 
    @Override
    public String getDetails() {
        return "help - Shows this help message";
    }

    @Override
    public String toString() {
        return output;
    }
    

}
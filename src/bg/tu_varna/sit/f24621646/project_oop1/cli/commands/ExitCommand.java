package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

public class ExitCommand implements Command{
    private String output;
    
    @Override
    public void execute(String[] args) {
        this.output = "Exiting the program...";
    }


    @Override
    public String getUsage() { 
        return "exit"; 
    }

    @Override
    public String getDescription() { 
        return "exits the program"; 
    }


    @Override
    public String toString() {
        return  output;
    }
    
}
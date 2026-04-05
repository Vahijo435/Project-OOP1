package bg.tu_varna.sit.f24621646.project_oop1;

public class ExitCommand implements Command{

    
    @Override
    public void execute(String[] args) {
        OutputManager.getWriter().write("Exiting the program...");
    }


    @Override
    public String getUsage() { 
        return "exit"; 
    }

    @Override
    public String getDescription() { 
        return "exits the program"; 
    }
}
package bg.tu_varna.sit.f24621646.project_oop1.cli.commands;

public interface Command {
    void execute(String[] args);
    String getUsage(); 
    String getDescription();
}

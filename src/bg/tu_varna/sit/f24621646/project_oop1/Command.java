package bg.tu_varna.sit.f24621646.project_oop1;

public interface Command {
    void execute(String[] args);
    String getUsage(); 
    String getDescription();
}

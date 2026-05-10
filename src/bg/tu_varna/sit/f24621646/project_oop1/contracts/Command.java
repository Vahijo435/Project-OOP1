package bg.tu_varna.sit.f24621646.project_oop1.contracts;

public interface Command {
    String execute(String[] args);
    String getUsage();
    String getDetails();
}

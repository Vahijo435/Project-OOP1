package bg.tu_varna.sit.f24621646.project_oop1;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void write(String message) {
        System.out.print(message);
    }
}
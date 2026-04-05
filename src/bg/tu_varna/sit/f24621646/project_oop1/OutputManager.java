package bg.tu_varna.sit.f24621646.project_oop1;

public class OutputManager {
    private static final OutputWriter writer = new ConsoleWriter();

    public static OutputWriter getWriter() {
        return writer;
    }
}

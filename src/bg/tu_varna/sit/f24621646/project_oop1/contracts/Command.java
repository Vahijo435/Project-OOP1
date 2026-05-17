package bg.tu_varna.sit.f24621646.project_oop1.contracts;
/**
 * @author Vahan
 * Интерфейс, който трябва да бъде имплементиран от всички команди в системата.
 * Дефинира методи за изпълнение на логиката и за предоставяне на помощна информация.
 *
 */
public interface Command {
    String execute(String[] args);
    String getUsage();
    String getDetails();
}

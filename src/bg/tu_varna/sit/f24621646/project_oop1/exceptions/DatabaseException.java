package bg.tu_varna.sit.f24621646.project_oop1.exceptions;
/**
 * @author Vahan
 * Потребителско изключение за нуждите на проекта за хвърляне при грешки
 *
 */
public class DatabaseException extends RuntimeException {
        public DatabaseException(String message) {
            super(message);
    }
}

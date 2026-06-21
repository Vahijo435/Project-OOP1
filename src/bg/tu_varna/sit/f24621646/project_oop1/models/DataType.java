package bg.tu_varna.sit.f24621646.project_oop1.models;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.DoubleValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.IntValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.NullValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.StringValue;
/**
 * @author Vahan
 * Енумерация, дефинираща поддържаните типове данни в базата (INTEGER, DOUBLE, STRING).
 *
 */
public enum DataType {
    INTEGER {
        @Override
        protected Value parseValue(String trimmed) {
            return new IntValue(Integer.parseInt(trimmed));
        }
        @Override
        public boolean isNumeric() {
            return true;
        }
    },
    DOUBLE {
        @Override
        protected Value parseValue(String trimmed) {
            return new DoubleValue(Double.parseDouble(trimmed));
        }
        @Override
        public boolean isNumeric() {
            return true;
        }
    },
    STRING {
        @Override
        protected Value parseValue(String trimmed) {
            if (trimmed.length() >= 2 && trimmed.startsWith("\"") && trimmed.endsWith("\"")) {
                return new StringValue(trimmed);
            } else {
                throw new DatabaseException("Символните низове трябва да са оградени в кавички.");
            }
        }
        @Override
        public boolean isNumeric() {
            return false;
        }
    };
    /**
     * Абстрактен метод, който всеки тип имплементира за създаване на конкретен Value обект.
     *
     */
    protected abstract Value parseValue(String trimmed);
    /**
     * Проверява дали типът е числов.
     */
    public abstract boolean isNumeric();

    /**
     * Главен метод за парсване на текст. Проверява за NULL стойности преди да делегира на конкретния тип.
     *
     */
    public Value parse(String raw) {
        if (raw == null || raw.equalsIgnoreCase("NULL") || raw.trim().isEmpty()) {
            return new NullValue();
        }
        return parseValue(raw.trim());
    }
}

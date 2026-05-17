package bg.tu_varna.sit.f24621646.project_oop1.models;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.DoubleValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.IntValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.NullValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.StringValue;
/**
 * @author Vahan
 * Енумерация, дефинираща поддържаните типове данни в базата (INTEGER, DOUBLE, STRING).
 * Предоставя метод за парсване на текст към конкретен Value обект.
 *
 */
public enum DataType {
    INTEGER,
    DOUBLE,
    STRING;

    public Value parse(String raw) {
        if (raw == null || raw.equalsIgnoreCase("NULL") || raw.trim().isEmpty()) {
            return new NullValue();
        }
        
        String trimmed = raw.trim();
        return switch (this) {
            case INTEGER -> new IntValue(Integer.parseInt(trimmed));
            case DOUBLE  -> new DoubleValue(Double.parseDouble(trimmed));
            case STRING  -> new StringValue(trimmed);
        };
    }
}

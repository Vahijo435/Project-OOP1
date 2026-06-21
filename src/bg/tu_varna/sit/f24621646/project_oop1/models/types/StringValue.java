package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;

/**
 * @author Vahan
 * Имплементация на интерфейса Value, съхраняваща низова стойност.
 *
 */
public class StringValue implements Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    @Override
    public double getAsDouble() {
        throw new DatabaseException("Символен низ не може да бъде използван за математически операции.");
    }
    @Override
    public String getAsString() {
        return value;
    }


    @Override
    public boolean isNumeric() {
        return false;
    }

    @Override
    public boolean matches(Value other) {
        if (this.isNull() || other.isNull()) {
            return this.isNull() && other.isNull();
        }
        if (this.isNumeric() != other.isNumeric()) {
            return false;
        }
        return this.getAsString().equals(other.getAsString());
    }

    @Override
    public boolean isNull() {
        return false;
    }
}


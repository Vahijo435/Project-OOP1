package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;

/**
 * @author Vahan
 * Имплементация на интерфейса Value, представляваща празна клетка.
 *
 */
public class NullValue implements Value {
    @Override
    public double getAsDouble() {
        throw new DatabaseException("NULL стойност не може да бъде използвана за математически операции.");

    }

    @Override
    public String getAsString() {
        return "NULL";
    }

    @Override
    public boolean isNull() {
        return true;
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
        return this.getAsString().equals(other.getAsString());
    }
}

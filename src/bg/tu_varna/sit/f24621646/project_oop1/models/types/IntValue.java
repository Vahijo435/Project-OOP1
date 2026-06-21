package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
/**
 * @author Vahan
 * Имплементация на интерфейса Value, съхраняваща целочислена  стойност.
 *
 */
public class IntValue implements Value {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }
    @Override
    public String getAsString() {
        return String.valueOf(value);
    }
    @Override
    public double getAsDouble() {
        return  value;
    }

    @Override public boolean isNull() {
        return false;
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean matches(Value other) {
        if (other.isNull()) return false;
        if (!other.isNumeric()) {
            return false;
        }
        return this.getAsDouble() == other.getAsDouble();
    }
}

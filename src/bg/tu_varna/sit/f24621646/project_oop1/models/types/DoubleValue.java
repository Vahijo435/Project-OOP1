package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
/**
 * @author Vahan
 * Имплементация на интерфейса Value, съхраняваща дробна стойност.
 *
 */
public class DoubleValue implements Value {
    private final double value;
    public DoubleValue(double value) {
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
    @Override
    public boolean isNull() {
        return false;
    }


    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean matches(Value other) {
        if(other.isNull()) return false;
        if (!other.isNumeric()) {
            return false;
        }
        return this.getAsDouble() == other.getAsDouble();
    }
}

package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
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
    public DataType getType() {
        return DataType.DOUBLE;
    }

    @Override
    public String getAsString() {
        return String.valueOf(value);
    }

    @Override
    public Object getRawValue() {
        return value;
    }
}

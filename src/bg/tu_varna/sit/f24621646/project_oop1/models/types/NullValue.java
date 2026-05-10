package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;

public class NullValue implements Value {
    @Override
    public DataType getType() {
        return null;
    }

    @Override
    public String getAsString() {
        return "NULL";
    }

    @Override
    public Object getRawValue() {
        return null;
    }
}

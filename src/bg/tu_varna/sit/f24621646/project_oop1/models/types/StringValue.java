package bg.tu_varna.sit.f24621646.project_oop1.models.types;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;

public class StringValue implements Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    @Override
    public DataType getType() { return DataType.STRING; }
    @Override public String getAsString() { return value; }
    @Override public Object getRawValue() { return value; }
}


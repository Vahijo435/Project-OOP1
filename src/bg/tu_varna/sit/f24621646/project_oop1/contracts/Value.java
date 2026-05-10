package bg.tu_varna.sit.f24621646.project_oop1.contracts;

import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;

public interface Value {
    DataType getType();
    String getAsString();
    Object getRawValue();
}

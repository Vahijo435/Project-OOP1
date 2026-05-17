package bg.tu_varna.sit.f24621646.project_oop1.contracts;

import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
/**
 * @author Vahan
 * Интерфейс, представляващ абстрактна стойност в конкретна клетка от таблицата.
 * Дефинира общи методи за извличане на типа и реалната стойност на данните.
 *
 */
public interface Value {
    DataType getType();
    String getAsString();
    Object getRawValue();
}

package bg.tu_varna.sit.f24621646.project_oop1.contracts;

import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
/**
 * @author Vahan
 * Интерфейс, представляващ абстрактна стойност в конкретна клетка от таблицата.
 * Дефинира общи методи за извличане на типа и реалната стойност на данните.
 *
 */
public interface Value {
    /**
     * Връща типа на данните за текущата стойност.
     * Обект от тип DataType (INTEGER, DOUBLE, STRING).
     */
    double getAsDouble();
    /**
     * Връща текстово  представяне на стойността (форматирано за извеждане) в клетката.
     */
    String getAsString();
    /**
     * Проверява дали стойността е празна (NULL).
     */
    boolean isNull();
    /**
     * Проверява дали стойността е от числов тип (Integer или Double).
     *
     */
    boolean isNumeric();
    /**
     * Метод за сравнение на две стойности.
     * Всеки конкретен тип сам определя правилата за съвпадение.
     *
     */
     boolean matches(Value other);
}

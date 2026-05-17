package bg.tu_varna.sit.f24621646.project_oop1.models;
/**
 * @author Vahan
 * Клас, описващ метаданните на една колона в таблицата - нейното име и тип на данните, които може да съхранява.
 *
 *
 */
public class Column {
    private final String name;
    private final DataType type;

    public Column(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

}

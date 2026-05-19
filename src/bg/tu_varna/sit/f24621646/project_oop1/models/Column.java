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
    /**
     * Създава нова колона със зададено име и тип на данните.
     *
     */
    public Column(String name, DataType type) {
        this.name = name;
        this.type = type;
    }
    /**
     * Връща името на колоната.
     *
     */
    public String getName() {
        return name;
    }
    /**
     * Връща типа на данните за тази колона.
     *
     */
    public DataType getType() {
        return type;
    }

}

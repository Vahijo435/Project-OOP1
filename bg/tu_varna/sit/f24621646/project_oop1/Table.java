package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final String name;
    private final List<Column> columns;
    private final List<Row> rows;

    public Table(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return new ArrayList<>(columns);
    }

    public List<Row> getRows() {
        return new ArrayList<>(rows);
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public void removeRow(int index) {
        rows.remove(index);
    }
}

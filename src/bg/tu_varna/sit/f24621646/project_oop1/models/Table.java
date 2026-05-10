package bg.tu_varna.sit.f24621646.project_oop1.models;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private String fileName;
    private final List<Column> columns;
    private final List<Row> rows;

    public Table(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }
    public void removeRow(Row row) {
        this.rows.remove(row);
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Column> getColumns() {
        return new ArrayList<>(columns);
    }

    public List<Row> getRows() {
        return new ArrayList<>(rows);
    }

    public void addColumn(Column column) {
        columns.add(column);
        for (Row row : rows) {
            row.addValue(null);
        }
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public void setName(String newName) {
    this.name = newName;
    }
}

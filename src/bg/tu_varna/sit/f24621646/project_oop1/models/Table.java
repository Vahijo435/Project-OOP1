package bg.tu_varna.sit.f24621646.project_oop1.models;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.NullValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vahan
 * Клас, представляващ таблица в базата от данни.
 * Съхранява структурата (списък от колони), съдържанието (списък от редове) и връзката с файла на таблицата.
 *
 */
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
            row.addValue(new NullValue());
        }
    }

    public void addRow(Row row) {
        if (row.getSize() != this.columns.size()) {
            throw new DatabaseException("Опит неуспешен за добавяне на ред с " + row.getSize() +
                    " стойности в таблица с " + this.columns.size() + " колони.");
        }
        rows.add(row);
    }

    public List<Row> findRowsByColumnValue(int colIndex, String rawSearchValue) {
        if (colIndex < 0 || colIndex >= columns.size()) {
            throw new DatabaseException("Невалиден номер на колона. Валидните номера са от 1 до " + columns.size());
        }

        Column targetColumn = columns.get(colIndex);
        Value expectedValue;
        try {
            expectedValue = targetColumn.getType().parse(rawSearchValue);
        } catch (Exception e) {
            throw new DatabaseException("Невалидна стойност за търсене спрямо типа на колоната.");
        }

        List<Row> matchingRows = new ArrayList<>();
        for (Row row : rows) {
            Value cellValue = row.getValue(colIndex);
            if (Objects.equals(cellValue.getRawValue(), expectedValue.getRawValue())) {
                matchingRows.add(row);
            }
        }

        return matchingRows;
    }

    public void setName(String newName) {
    this.name = newName;
    }
}

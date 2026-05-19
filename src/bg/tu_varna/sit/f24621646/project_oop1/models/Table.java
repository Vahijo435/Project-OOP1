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

    /**
     * Създава нова таблица.
     * name - Името на таблицата.
     * fileName - Пътят до файла, съхраняващ данните за таблицата.
     */
    public Table(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    /**
     * Връща името на таблицата.
     *
     */
    public String getName() {
        return name;
    }
    /**
     * Връща пътя на файла на таблицата.
     *
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Премахва подадения ред от таблицата.
     *
     */
    public void removeRow(Row row) {
        this.rows.remove(row);
    }
    /**
     * Задава нов път към файла за запис/четене на таблицата.
     *
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Връща списък с колоните на таблицата.
     *
     */
    public List<Column> getColumns() {
        return new ArrayList<>(columns);
    }
    /**
     * Връща списък с всички редове в таблицата.
     *
     */
    public List<Row> getRows() {
        return new ArrayList<>(rows);
    }
    /**
     * Добавя нова колона към таблицата.
     * Запълва клетките за тази колона във всички съществуващи редове с NULL.
     *
     */
    public void addColumn(Column column) {
        columns.add(column);
        for (Row row : rows) {
            row.addValue(new NullValue());
        }
    }
    /**
     * Добавя нов ред към таблицата.
     * Валидира дали броят на стойностите съвпада с броя на колоните.
     * row - Редът за добавяне.
     * връща Exception Ако броят на клетките в реда не отговаря на броя на колоните.
     */
    public void addRow(Row row) {
        if (row.getSize() != this.columns.size()) {
            throw new DatabaseException("Опит неуспешен за добавяне на ред с " + row.getSize() + " стойности в таблица с " + this.columns.size() + " колони.");
        }
        rows.add(row);
    }
    /**
     * Намира и връща списък от всички редове, чиято стойност в дадена колона съвпада с подадената търсена стойност.
     * colIndex - Индексът на колоната за търсене.
     * rawSearchValue - Търсената стойност под формата на низ.
     */
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
    /**
     * Променя името на таблицата.
     *
     */
    public void setName(String newName) {
    this.name = newName;
    }
}

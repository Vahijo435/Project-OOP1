package bg.tu_varna.sit.f24621646.project_oop1.models;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.DoubleValue;
import bg.tu_varna.sit.f24621646.project_oop1.models.types.NullValue;

import java.util.ArrayList;
import java.util.List;

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
     * Намира редовете, чиято стойност съвпада с подадения обект Value. Връща списък от всички редове, чиято стойност в дадена колона съвпада с подадената търсена стойност.
     * colIndex - Индексът на колоната за търсене.
     * expectedValue - Стойност за сравняване под формата на Value обект
     */
    public List<Row> findRowsByValue(int colIndex, Value expectedValue) {
        List<Row> matchingRows = new ArrayList<>();
        for (Row row : rows) {
            Value cellValue = row.getValue(colIndex);
            if (cellValue.matches(expectedValue)) {
                matchingRows.add(row);
            }
        }
        return matchingRows;
    }

    /**
     * Парсва низ към Value и търси съвпадения в колоната.
     * colIndex - Индексът на колоната за търсене.
     * rawSearchValue - Търсената стойност под формата на низ.
     */
    public List<Row> findRowsByColumnValue(int colIndex, String rawSearchValue) {
        Column targetColumn = columns.get(colIndex);
        Value expectedValue = targetColumn.getType().parse(rawSearchValue);
        return findRowsByValue(colIndex, expectedValue);
    }
    /**
     * Променя името на таблицата.
     *
     */
    public void setName(String newName) {
    this.name = newName;
    }

    /**
     * Парсва и валидира номер на колона от стринг
     *
     */
    public int parseColumnIndex(String colStr) {
        try {
            int index = Integer.parseInt(colStr) - 1;
            if (index < 0 || index >= columns.size()) {
                throw new DatabaseException("Невалиден номер на колона. Валидните номера са от 1 до " + columns.size() + ".");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new DatabaseException("Номерът на колоната трябва да бъде число.");
        }
    }
}

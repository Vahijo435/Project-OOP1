package bg.tu_varna.sit.f24621646.project_oop1.models;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Vahan
 * Клас, представляващ един ред в таблицата.
 * Съдържа списък от стойности, които отговарят на колоните в таблицата.
 *
 */
public class Row {
    private final List<Value> cells;

    public Row(List<Value> cells) {
        this.cells = new ArrayList<>(cells);
    }

    public Value getValue(int index) {
        return cells.get(index);
    }
    public void addValue(Value value) {
     this.cells.add(value);
    }
    public String getDisplayValue(int index) {
        Value val = getValue(index);
        if (val == null) return "NULL";
        return val.getAsString();
    }

    public int getSize() {
        return cells.size();
    }

    public List<Value> getValues() {
        return new ArrayList<>(cells);
    }
    public void setValue(int index, Value value) {
    if (index >= 0 && index < cells.size()) {
        cells.set(index, value);
    }
}
}
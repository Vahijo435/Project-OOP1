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
    /**
     * Създава нов ред с предварително подаден списък от стойности от тип Value.
     *
     */
    public Row(List<Value> cells) {
        this.cells = new ArrayList<>(cells);
    }
    /**
     * Връща стойността в клетка на определен индекс.
     *
     */
    public Value getValue(int index) {
        return cells.get(index);
    }
    /**
     * Добавя нова стойност в края на реда.
     *
     */
    public void addValue(Value value) {
     this.cells.add(value);
    }
    /**
     * Връща текстовото представяне на стойността на даден индекс, готово за извеждане в конзолата.
     *
     */
    public String getDisplayValue(int index) {
        Value val = getValue(index);
        if (val == null || val.isNull()) return "NULL";
        return val.getAsString();
    }
    /**
     * Връща броя на колоните в този ред.
     *
     */
    public int getSize() {
        return cells.size();
    }
    /**
     * Връща копие на списъка с всички стойности в реда.
     *
     */
    public List<Value> getValues() {
        return new ArrayList<>(cells);
    }
    /**
     * Презаписва стойността на съществуваща клетка в реда.
     *
     */
    public void setValue(int index, Value value) {
    if (index >= 0 && index < cells.size()) {
        cells.set(index, value);
    }
}
}
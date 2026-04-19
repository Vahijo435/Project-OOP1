package bg.tu_varna.sit.f24621646.project_oop1.models;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private final List<Object> values;

    public Row(List<Object> values) {
        this.values = new ArrayList<>(values);
    }

    public Object getValue(int index) {
        return values.get(index);
    }
    public void addValue(Object value) {
     this.values.add(value);
    }
    public String getDisplayValue(int index) {
        Object val = getValue(index);
        if (val == null) return "NULL";
        return val.toString();
    }

    public int getSize() {
        return values.size();
    }

    public List<Object> getValues() {
        return new ArrayList<>(values);
    }
    public void setValue(int index, Object value) {
    if (index >= 0 && index < values.size()) {
        values.set(index, value);
    }
}
}
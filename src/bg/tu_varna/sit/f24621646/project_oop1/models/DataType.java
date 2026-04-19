package bg.tu_varna.sit.f24621646.project_oop1.models;

public enum DataType {
    INTEGER,
    DOUBLE,
    STRING;

    public Object parse(String raw) {
        if (raw == null || raw.equalsIgnoreCase("NULL")) {
            return null;
        }
        
        String trimmed = raw.trim();
        return switch (this) {
            case INTEGER -> Integer.parseInt(trimmed);
            case DOUBLE  -> Double.parseDouble(trimmed);
            case STRING  -> trimmed;
        };
    }
}

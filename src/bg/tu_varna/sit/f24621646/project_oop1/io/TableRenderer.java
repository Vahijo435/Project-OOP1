package bg.tu_varna.sit.f24621646.project_oop1.io;

import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;

import java.util.List;

public class TableRenderer {

    public static String render(List<Column> columns, List<Row> rows) {
        if (columns.isEmpty()) return "Таблицата няма дефинирани колони.";
        if (rows.isEmpty()) return "Таблицата е празна.";

        int[] colWidths = calculateWidths(columns, rows);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < columns.size(); i++) {
            sb.append(String.format("%-" + colWidths[i] + "s", columns.get(i).getName()));
            if (i < columns.size() - 1) sb.append(" | ");
        }
        sb.append("\n");

        for (int i = 0; i < columns.size(); i++) {
            sb.repeat("-", colWidths[i]);
            if (i < columns.size() - 1) sb.append("-|-");
        }
        sb.append("\n");

        for (Row row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                sb.append(String.format("%-" + colWidths[i] + "s", row.getDisplayValue(i)));
                if (i < columns.size() - 1) sb.append(" | ");
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }

    private static int[] calculateWidths(List<Column> columns, List<Row> rows) {
        int[] widths = new int[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            widths[i] = columns.get(i).getName().length();
        }

        for (Row row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                String val = row.getDisplayValue(i);
                if (val.length() > widths[i]) {
                    widths[i] = val.length();
                }
            }
        }
        return widths;
    }
}

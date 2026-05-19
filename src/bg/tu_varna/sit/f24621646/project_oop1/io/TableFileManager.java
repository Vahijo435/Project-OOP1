package bg.tu_varna.sit.f24621646.project_oop1.io;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Vahan
 * Клас, отговорен за входно-изходните операции на индивидуални таблици.
 * Реализира логиката за четене от файл и записване на структурата и редовете във файл.
 *
 */
public class TableFileManager {

    /**
     * Зарежда данни за конкретна таблица от текстов файл.
     *
     */
    public static void loadTable(String filePath, String tableName, Database database) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        Table table = new Table(tableName, filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine == null || headerLine.trim().isEmpty()) {
                database.addTable(table);
                return;
            }

            String[] columnDefs = headerLine.trim().split("\\|");
            for (String def : columnDefs) {
                String[] parts = def.split(":");
                if (parts.length == 2) {
                    String colName = parts[0].trim();
                    DataType type = DataType.valueOf(parts[1].trim().toUpperCase());
                    table.addColumn(new Column(colName, type));
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split("\\|");
                if (tokens.length != table.getColumns().size()) {
                    continue;
                }

                List<Value> values = new ArrayList<>();
                for (int i = 0; i < tokens.length; i++) {
                    String token = tokens[i].trim();
                    Column col = table.getColumns().get(i);
                    try {
                        values.add(col.getType().parse(token));
                    } catch (Exception e) {
                        throw new DatabaseException(e.getMessage());
                    }
                }
                    table.addRow(new Row(values));

            }

            database.addTable(table);
        } catch (IOException e) {
            throw new DatabaseException("Чененето на таблицата неуспешно: " + e.getMessage());
        }
    }
    /**
     * Записва съдържанието и структурата на таблица във файл.
     *
     */
    public static void saveTable(Table table, String filePath) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            List<Column> columns = table.getColumns();

            for (int i = 0; i < columns.size(); i++) {
                Column col = columns.get(i);
                bw.write(col.getName() + ":" + col.getType().name());
                if (i < columns.size() - 1) {
                    bw.write(" | ");
                }
            }
            bw.newLine();

            for (Row row : table.getRows()) {
                for (int i = 0; i < columns.size(); i++) {
                    Object raw = row.getValue(i).getRawValue();
                    if (raw == null) {
                        bw.write("NULL");
                    } else {
                        bw.write(raw.toString());
                    }
                    if (i < columns.size() - 1) {
                        bw.write(" | ");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DatabaseException("Записът на таблицата е неуспешно: " + e.getMessage());
        }

    }

    }

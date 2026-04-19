package bg.tu_varna.sit.f24621646.project_oop1.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Database currentDatabase = null;
    private String currentCatalogFilePath = null;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean isDatabaseOpen() {
        return currentDatabase != null;
    }

    public Database getDatabase() {
        return currentDatabase;
    }

    public void loadTableFromFile(String fileName, String tableName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        Table table = new Table(tableName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine != null && !headerLine.trim().isEmpty()) {

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
                    if (line.isEmpty())
                        continue;

                    String[] tokens = line.split("\\|");
                    if (tokens.length != table.getColumns().size()) {
                        continue;
                    }

                    List<Object> values = new ArrayList<>();
                    boolean validRow = true;
                    for (int i = 0; i < tokens.length; i++) {
                        String token = tokens[i].trim();
                        Column col = table.getColumns().get(i);
                        try {
                            values.add(col.getType().parse(token));
                        } catch (Exception e) {
                            validRow = false;
                            break;
                        }
                    }
                    if (validRow) {
                        table.addRow(new Row(values));
                    }
                }
            }
            if (getDatabase() != null) {
                getDatabase().addTable(table, fileName);
            }
        } catch (IOException e) {
            throw new DatabaseException("Error opening file: " + e.getMessage());
        }

    }
        public void saveTableToFile(Table table, String fileName){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
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
                    bw.write(row.getDisplayValue(i));
                    if (i < columns.size() - 1) {
                        bw.write(" | ");
                    }
                }
                bw.newLine();
            }
        }catch (IOException e) {
        throw new DatabaseException(e.getMessage());
    }
    }
    public String getCurrentCatalogFilePath() {
        return currentCatalogFilePath;
    }

    public void setCurrentCatalogFilePath(String path) {
        this.currentCatalogFilePath = path;
    }

    public void openNewDatabase(String path) {
        this.currentDatabase = new Database();
        this.currentCatalogFilePath = path;
    }

    public void closeDatabase() {
        this.currentDatabase = null;
        this.currentCatalogFilePath = null;
    }
}
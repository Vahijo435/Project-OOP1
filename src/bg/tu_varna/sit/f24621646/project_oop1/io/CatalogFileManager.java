package bg.tu_varna.sit.f24621646.project_oop1.io;

import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

import java.io.*;
/**
 * @author Vahan
 * Клас, отговорен за управлението на каталожния файл на базата данни.
 * Чете и записва списъка с всички таблици и пътищата към техните файлове.
 *
 */
public class CatalogFileManager {
    /**
     * Чете каталожен файл и зарежда таблиците в подадената база от данни.
     *
     */
    public static void readCatalog(String catalogPath, Database database) {
        File catalogFile = new File(catalogPath);
        if (!catalogFile.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(catalogFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String tableName = parts[0].trim();
                    String tableFileName = parts[1].trim();
                    TableFileManager.loadTable(tableFileName, tableName, database);
                }
            }
        } catch (IOException e) {
            throw new DatabaseException("Четенето в каталожния файл неуспешно: " + e.getMessage());
        }
    }

    /**
     * Записва информацията за таблиците на подадената база от данни в каталожен файл.
     *
     */
    public static void writeCatalog(String catalogPath, Database database) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(catalogPath))) {
            for (Table table : database.getTables().values()) {
                bw.write(table.getName() + "," + table.getFileName());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DatabaseException("Писането в каталожния файл неуспешно: " + e.getMessage());
        }
    }
}

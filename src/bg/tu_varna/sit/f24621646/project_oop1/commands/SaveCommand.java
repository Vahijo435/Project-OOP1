package bg.tu_varna.sit.f24621646.project_oop1.commands;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;


import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.io.CatalogFileManager;
import bg.tu_varna.sit.f24621646.project_oop1.io.TableFileManager;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

/**
 * @author Vahan
 * Команда за запазване на всички направени промени в текущата база от данни.
 * Презаписва каталожния файл и файловете на всички заредени таблици.
 *
 */
public class SaveCommand implements Command {


    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "В момента няма отворена база данни.";
        }

        Database db = manager.getDatabase();
        String catalogPath = manager.getCurrentCatalogFilePath();

        try  {
            CatalogFileManager.writeCatalog(catalogPath, db);

            for (Table table : db.getTables().values()) {
                TableFileManager.saveTable(table,table.getFileName());
            }

            return "Успешно запазено в " + catalogPath;
        } catch (Exception e) {
            throw new DatabaseException("Грешка при запазване на файл: " + e.getMessage());
        }
    }

    @Override 
    public String getUsage() {
        return "save";
    }
    public String getDetails(){
       return "запазва текущо отворения файл";

    }


}
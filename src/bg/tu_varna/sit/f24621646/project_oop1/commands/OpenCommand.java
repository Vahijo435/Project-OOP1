package bg.tu_varna.sit.f24621646.project_oop1.commands;


import java.io.File;

import java.io.IOException;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.io.CatalogFileManager;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

/**
 * @author Vahan
 * Команда за отваряне на съществуваща база от данни чрез каталожен файл или за създаване на нова база и нов файл, ако такъв не съществува.
 *
 */
public class OpenCommand implements Command {

     @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
         if(manager.isDatabaseOpen()){
             return "Има вече отворена съществуваща база от данни.  Моля, първо затворете текущата с командата 'close'.";
         }
        if (args.length == 1) {
            return "Липсващ път към файл. Начин на употреба:" + getUsage();
        }
        String filePath = args[1];

         if (!filePath.endsWith(".txt")) {
             filePath = filePath + ".txt";
         }

        File file = new File(filePath);

         try {
             manager.openNewDatabase(filePath);

             if (file.exists()) {
                 CatalogFileManager.readCatalog(filePath, manager.getDatabase());
                 return "Успешно беше отворен: " + file.getName();

             } else {
                 boolean createNew = file.createNewFile();
                 if (!createNew) {
                     manager.closeDatabase();
                     return "Грешка при отваряне: Не може да се създаде файлът.";
                 }
                 return "Успешно беше създаден и отворен: " + file.getName();

             }
         } catch (IOException e) {
             manager.closeDatabase();
             throw new DatabaseException("Грешка при отваряне: " + e.getMessage());
         }
     }


    @Override
    public String getUsage() {
        return "open <file>";
    }
    public String getDetails(){
         return "отваря файл";
    }




}

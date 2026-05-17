package bg.tu_varna.sit.f24621646.project_oop1.commands;

import java.io.File;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.io.TableFileManager;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
/**
 * @author Vahan
 * Команда за импортиране на външна таблица от файл към текущо отворената база от данни.
 *
 */
public class ImportCommand implements Command {

public String execute(String[] args) {
    DatabaseManager manager = DatabaseManager.getInstance();

    if (!manager.isDatabaseOpen()) {
        return "Преди да започнете импорта, трябва първо да отворите базата данни.";

    }
    if (args.length < 2) {
        return "Липсва име на файл. Употреба: " + getUsage();
    }

    String fileName = args[1];
    if (!fileName.endsWith(".txt")) {
        fileName = fileName + ".txt";
    }
    
    File file = new File(fileName);
    if(!file.exists()){
        return "Указаната таблица не съществува";
    }
    String tableName = file.getName().replace(".txt", "");

    if (manager.getDatabase().hasTable(tableName)) {
        return "Таблица '" + tableName + "' вече съществува.";
    }

    try {
        TableFileManager.loadTable(fileName, tableName, manager.getDatabase());
    } catch (Exception e) {
        throw new DatabaseException("Грешка при импорт: " + e.getMessage());
    }
    return "Успешно импортирана на таблица'" + tableName + "' от " + fileName;
}
            

    @Override 
    public String getUsage() {
        return "import <file name>";
    }
    public String getDetails(){
    return "Добавя в базата данни нова таблица от файл.";
    }

}

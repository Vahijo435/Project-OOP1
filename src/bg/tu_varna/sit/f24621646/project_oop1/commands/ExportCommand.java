package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.io.TableFileManager;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
/**
 * @author Vahan
 * Команда за експортиране на конкретна таблица
 * във външен текстов файл по избор на потребителя.
 *
 */
public class ExportCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) return "Няма отворена база данни.";
        if (args.length < 3) return "Употреба:"+getUsage();
        String tableName=args[1];
        Database db = manager.getDatabase();
        if (!db.hasTable(tableName)) {
            return "Таблица '" + tableName + "' не съществува.";
        }
        Table table = db.getTable(tableName);
        String filePath = args[2];
        if(!filePath.endsWith(".txt")){
            filePath=filePath+".txt";
        }
        try {
            TableFileManager.saveTable(table, filePath);
            return "Таблицата беше успешно експортирана в : " + filePath;
        } catch (Exception e) {
            throw new DatabaseException("Грешка при експортиране: " + e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "export <name> <file name>";
    }
    @Override
    public String getDetails() {
        return "Записва дадена таблица във файл";
    }

}

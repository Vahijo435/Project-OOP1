package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;

import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
/**
 * @author Vahan
 * Команда за преименуване на съществуваща таблица в базата данни.
 *
 */
public class RenameCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "Грешка: Няма отворена база данни.";
        }

        if (args.length < 3) {
            return "Употреба: "+getUsage();
        }

        String oldName = args[1];
        String newName = args[2];
        Database db = manager.getDatabase();

        if (!db.hasTable(oldName)) {
            return "Таблица с име '" + oldName + "' не съществува.";
        }

        if (db.hasTable(newName)) {
            return "Вече съществува таблица с име '" + newName + "'.";
        }


        Table table = db.getTables().remove(oldName);

        table.setName(newName);
        db.getTables().put(newName, table);


        return "Таблицата '" + oldName + "' беше преименувана на '" + newName + "'";
    }

    @Override
    public String getUsage() {
        return "rename <old name> <new name>";
    }
    @Override
    public String getDetails(){
        return "Преименува съществуваща таблица";
    }
}

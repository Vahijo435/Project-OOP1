package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.io.TableRenderer;
/**
 * @author Vahan
 * Команда за отпечатване на цялото съдържание на дадена таблица в конзолата.
 *
 */
public class PrintCommand implements Command {
    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) return "В момента няма отворена база данни.";
        if (args.length < 2) return "Липсва име на таблица.";

        Table table = manager.getDatabase().getTable(args[1]);
        if (table == null) return "Таблица '" + args[1] + "' не беше намерена.";

        return TableRenderer.renderPaged(table.getColumns(), table.getRows());
    }

    @Override
    public String getUsage() {
        return "print <name>";
    }
    @Override
    public String getDetails(){
        return "Показва всички редове от таблицата";
    }


}

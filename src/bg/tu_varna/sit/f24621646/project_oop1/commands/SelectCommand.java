package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;
import bg.tu_varna.sit.f24621646.project_oop1.io.TableRenderer;

import java.util.List;
/**
 * @author Vahan
 * Команда за търсене и извличане на редове от дадена таблица.
 * Показва всички записи, които съдържат търсената стойност в посочената колона.
 */
public class SelectCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) return "Няма отворена база данни.";
        if (args.length < 4) return "Употреба: "+getUsage();

        Table table = manager.getDatabase().getTable(args[3]);
        if (table == null) return "Таблица не съществува.";
        int colIndex;
        try {
            colIndex = Integer.parseInt(args[1])-1;
        } catch (NumberFormatException e) {
            throw new DatabaseException("Номерът на колоната трябва да бъде число.");
        }
        String searchVal = args[2];

        List<Row> matchingRows = table.findRowsByColumnValue(colIndex, searchVal);

        return TableRenderer.renderPaged(table.getColumns(), matchingRows);
    }

    @Override
    public String getUsage() {
        return "select <column-n> <value> <table name>";
    }
    public String getDetails(){
        return "Извежда всички редове от таблицата, които съдържат стойността 'value' в клетката с дадения пореден номер";
    }
}

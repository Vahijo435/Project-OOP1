package bg.tu_varna.sit.f24621646.project_oop1.commands;
import java.util.Set;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class ShowTablesCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "В момента няма отворена база данни.";
        }

        Set<String> tableNames = manager.getDatabase().getTables().keySet();

        if (tableNames.isEmpty()) {
            return "Все още не са импортирани таблици.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String name : tableNames) {
                sb.append(name).append("\n");
            }
            return sb.toString().trim();
        }
    }


    @Override
    public String getUsage() {
        return "showtables";
    }
    public String getDetails(){
        return "показва всички импортирани таблици";
    }

}

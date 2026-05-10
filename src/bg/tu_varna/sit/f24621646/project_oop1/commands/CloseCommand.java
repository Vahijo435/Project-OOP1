package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class CloseCommand implements Command {

    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();

        if (!manager.isDatabaseOpen()) {
            return "В момента няма отворена база от данни.";
        }
        manager.closeDatabase();
        return "Успешно затворена база от данни. Незапазените промени са изгубени.";
    }

    @Override 
    public String getUsage() {
        return "close";
    }
    public  String getDetails(){
        return "затваря текущо отворен файл";
    }
}

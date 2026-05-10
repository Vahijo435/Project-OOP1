package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.manager.DatabaseManager;

public class SaveAsCommand implements Command {


    @Override
    public String execute(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance();
        if (!manager.isDatabaseOpen()) {
            return "В момента няма отворена база данни.";
        }
        if (args.length < 3) {
            return "Липсващ път към файла. Употреба: "+getUsage();
        }
  
        String newPath = args[2]+".txt";
        
        manager.setCurrentCatalogFilePath(newPath);
        
        new SaveCommand().execute(new String[]{"save"});
        
        return "Успешно запазен като " + newPath;
    }

    @Override 
    public String getUsage() {
        return "save as <файл>";
    }

    @Override
    public String getDetails(){
        return "запазва текущо отворения файл на указаното място";
    }


}
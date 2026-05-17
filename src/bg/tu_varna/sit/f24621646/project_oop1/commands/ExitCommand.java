package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
/**
 * @author Vahan
 *
 * Команда за прекратяване на изпълнението на програмата.
 *
 */
public class ExitCommand implements Command {

    @Override
    public String execute(String[] args) {
        System.exit(0);
        return "";
    }


    @Override
    public String getUsage() {
        return "exit";
    }

    @Override
    public String getDetails(){
        return "излиза от програмата";
    }


}
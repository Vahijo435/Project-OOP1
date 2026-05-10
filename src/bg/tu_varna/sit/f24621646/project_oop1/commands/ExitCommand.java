package bg.tu_varna.sit.f24621646.project_oop1.commands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;

public class ExitCommand implements Command {

    @Override
    public String execute(String[] args) {
        return "Излизане от програмата...";
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
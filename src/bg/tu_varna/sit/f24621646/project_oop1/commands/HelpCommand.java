package bg.tu_varna.sit.f24621646.project_oop1.commands;


import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
import bg.tu_varna.sit.f24621646.project_oop1.invoker.CLInvoker;

/**
 * @author Vahan
 * Команда за извеждане на помощен списък с всички поддържани команди,техния синтаксис и кратко описание на действието им.
 */
public class HelpCommand implements Command {
    private final CLInvoker cli;

    public HelpCommand(CLInvoker cli) {
        this.cli = cli;

    }
    @Override
    public String execute(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Следните команди се поддържат:\n");

        for (Command cmd : cli.getCommands()) {
            sb.append(cmd.getUsage()).append(" - ").append(cmd.getDetails()).append("\n");
        }
        return sb.toString();
    }

 
    @Override
    public String getUsage() {
        return "help";
    }
    public String getDetails(){
        return "Показва това спомагателно съобщение";
    }

}
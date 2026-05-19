package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Scanner;

import bg.tu_varna.sit.f24621646.project_oop1.commands.*;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.invoker.CLInvoker;
/**
 * @author Vahan
 * Главен клас на приложението. Отговаря за стартирането на програмата, регистрацията на командите и управлението на основния цикъл за четене на потребителски вход.
 *
 */
public class Application {
    public static void main(String[] args) {
        CLInvoker cli = new CLInvoker();

        cli.reg("exit", new ExitCommand());
        cli.reg("open", new OpenCommand());
        cli.reg("close", new CloseCommand());
        cli.reg("save", new SaveCommand());
        cli.reg("saveas", new SaveAsCommand());
        cli.reg("import", new ImportCommand());
        cli.reg("print", new PrintCommand());
        cli.reg("describe", new DescribeCommand());
        cli.reg("showtables", new ShowTablesCommand());
        cli.reg("insert", new InsertCommand());
        cli.reg("rename", new RenameCommand());
        cli.reg("addcolumn", new AddColumnCommand());
        cli.reg("aggregate",new AggregateCommand());
        cli.reg("count",new CountCommand());
        cli.reg("delete",new DeleteCommand());
        cli.reg("export", new ExportCommand());
        cli.reg("innerjoin",new InnerJoinCommand());
        cli.reg("select",new SelectCommand());
        cli.reg("update",new UpdateCommand());


        cli.reg("help", new HelpCommand(cli));

        System.out.println("База от данни");
        System.out.println("Напишете 'help', за да видите наличните команди.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                try {
                    System.out.println(cli.process(line));
                }catch (DatabaseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }



    /**
     * Помощен метод за извеждане на текст в конзолата без преминаване на нов ред.
     *
     */
    public static void display(String s){
        System.out.print(s);
    }
    }


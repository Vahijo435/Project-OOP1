package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Scanner;

import bg.tu_varna.sit.f24621646.project_oop1.commands.*;
import bg.tu_varna.sit.f24621646.project_oop1.invoker.CLInvoker;

public class Application {
    public static void main(String[] args) {
        CLInvoker cli = new CLInvoker();

        cli.reg("exit", new ExitCommand());
        cli.reg("open", new OpenCommand());
        cli.reg("close", new CloseCommand());
        cli.reg("save", new SaveCommand());
        cli.reg("save as", new SaveAsCommand());
        cli.reg("import", new ImportCommand());
        cli.reg("print", new PrintCommand());
        cli.reg("describe", new DescribeCommand());
        cli.reg("showtables", new ShowTablesCommand());
        cli.reg("insert", new InsertCommand());
        cli.reg("rename", new RenameCommand());


        cli.reg("help", new HelpCommand(cli));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Database");
        boolean isRunning = true;

        while (isRunning) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                System.out.println(cli.process(line));
                String first = line.split("\\s+")[0].toLowerCase();
                if (first.equals("exit")) isRunning = false;
            }
        }
        scanner.close();
    }

    }


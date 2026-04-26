package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Scanner;

import bg.tu_varna.sit.f24621646.project_oop1.cli.CLI;
import bg.tu_varna.sit.f24621646.project_oop1.cli.commands.*;

public class Application {
    public static void main(String[] args) {
        CLI cli = new CLI();

        cli.reg("exit", new ExitCommand());
        cli.reg("open", new OpenCommand());
        cli.reg("close", new CloseCommand());
        cli.reg("save", new SaveCommand());
        cli.reg("save as", new SaveAsCommand());
        cli.reg("import", new ImportCommand());
        cli.reg("print", new PrintCommand());
        cli.reg("describe", new DescribeCommand());
        cli.reg("showtables", new ShowTablesCommand());

        cli.reg("help", new HelpCommand(cli));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Database");
        boolean isRunning = true;
        
        while (isRunning) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String output = cli.process(line);
                System.out.println(output);
                String first = line.split("\\s+")[0].toLowerCase();
                if (first.equals("exit")) isRunning = false; 
            }
        }
        scanner.close();
    }

    }


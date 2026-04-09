package bg.tu_varna.sit.f24621646.project_oop1;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        CLI cli = new CLI();
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


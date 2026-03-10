package bg.tu_varna.sit.f24621646.project_oop1;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


public class CLI {
private final Map<String, Command> commands=new HashMap<>();
    public CLI() {
        commands.put("exit", new ExitCommand());
    }

    public void start(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Database\n> ");
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()){
            String[] sp = line.split("\\s+");
            Command cmd = commands.get(sp[0].toLowerCase());
            String[] args=new String[sp.length-1];
            for(int i=1;i<sp.length;i++){
                args[i-1]=sp[i];
            }
            if(cmd !=null) cmd.execute(args);
            else System.out.println("Непозната команда - " + sp[0]);
            
        }
            System.out.print("> ");
        }
        scanner.close();

    }

}

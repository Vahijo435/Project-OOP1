package bg.tu_varna.sit.f24621646.project_oop1.invoker;

import java.util.*;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;

/**
 * @author Vahan
 * Клас, отговарящ за регистрирането на поддържаните команди и за парсването и насочването на потребителския вход към правилната команда.
 *
 */
public class CLInvoker {
private final Map<String, Command> commands=new LinkedHashMap<>();
    /**
     * Регистрира нова команда в инвокъра.
     * name - Името (ключът), чрез което ще се извиква командата.
     * command - Инстанция на клас, имплементиращ интерфейса Command.
     */
    public void reg(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    /**
     * Връща списък с обекти от тип Command с всички регистрирани команди.
     */
    public List<Command> getCommands() {
        return new ArrayList<>(commands.values());
    }

    /**
     * Разбива входния низ от конзолата на масив от аргументи. Обработва интервали вътре в кавички и екранирани символи.
     *
     */
    private String[] parseArgs(String line) {
        List<String> args = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean isInQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '|') {
                continue;
            }
            if (c == '\\' && i + 1 < line.length()) {
                char nextC = line.charAt(i + 1);
                if (nextC == '"' || nextC == '\\') {
                    cur.append(nextC);
                    i++;
                    continue;
                }
                cur.append(c);

            } else if (c == '"') {
                isInQuotes = !isInQuotes;
            } else if (Character.isWhitespace(c) && !isInQuotes) {
                if (!cur.isEmpty()) {
                    args.add(cur.toString());
                    cur = new StringBuilder();
                }
            } else {
                cur.append(c);
            }
        }
        if (!cur.isEmpty()) {
            args.add(cur.toString());
        }
        return args.toArray(new String[0]);
    }
    /**
     * Обработва подадения потребителски вход, намира съответната команда и я изпълнява. Връща резултатът от изпълнението на командата или съобщение за неразпозната команда.
     *
     */
    public String process(String line){
        String[] args = parseArgs(line);
        String commandName=args[0].toLowerCase();
        Command cmd = commands.get(commandName);

        if(cmd != null){
            return cmd.execute(args);
        } else return "Непозната команда: " + commandName + ".";
            }
        }

    

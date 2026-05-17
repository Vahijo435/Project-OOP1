package bg.tu_varna.sit.f24621646.project_oop1.io;

import bg.tu_varna.sit.f24621646.project_oop1.Application;
import bg.tu_varna.sit.f24621646.project_oop1.invoker.CLInvoker;
import bg.tu_varna.sit.f24621646.project_oop1.io.paginationCommands.NextCommand;
import bg.tu_varna.sit.f24621646.project_oop1.io.paginationCommands.PrevCommand;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;

import java.util.List;
import java.util.Scanner;
/**
 * @author Vahan
 * Помощен клас за визуално форматиране на таблици, включващо пагинация.
 * Изчислява необходимите ширини на колоните и генерира подравнен стринг за отпечатване в конзолата.
 *
 */
public class TableRenderer {

    public static String render(List<Column> columns, List<Row> rows) {
        if (columns.isEmpty()) return "Таблицата няма дефинирани колони.";
        if (rows.isEmpty()) return "Таблицата е празна.";

        int[] colWidths = calculateWidths(columns, rows);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < columns.size(); i++) {
            sb.append(String.format("%-" + colWidths[i] + "s", columns.get(i).getName()));
            if (i < columns.size() - 1) sb.append(" | ");
        }
        sb.append("\n");

        for (int i = 0; i < columns.size(); i++) {
            sb.repeat("-", colWidths[i]);
            if (i < columns.size() - 1) sb.append("-|-");
        }
        sb.append("\n");

        for (Row row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                sb.append(String.format("%-" + colWidths[i] + "s", row.getDisplayValue(i)));
                if (i < columns.size() - 1) sb.append(" | ");
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }

    private static int[] calculateWidths(List<Column> columns, List<Row> rows) {
        int[] widths = new int[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            widths[i] = columns.get(i).getName().length();
        }

        for (Row row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                String val = row.getDisplayValue(i);
                if (val.length() > widths[i]) {
                    widths[i] = val.length();
                }
            }
        }
        return widths;
    }
    public static String renderPaged(List<Column> columns, List<Row> rows) {
        Scanner scanner = new Scanner(System.in);
        if (columns.isEmpty()) return "Таблицата няма дефинирани колони.";
        if (rows.isEmpty()) return "Таблицата е празна.";

        int pageSize = 10;
        if(rows.size()<=pageSize){
            return render(columns, rows);
        }
        int totalPages = (int) Math.ceil((double) rows.size() / pageSize);
        int currentPage = 1;
        CLInvoker cli = new CLInvoker();
        PrevCommand prev=new PrevCommand();
        NextCommand next = new NextCommand(totalPages);
        cli.reg("prev", prev);
        cli.reg("next", next);
        while (true) {
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, rows.size());

            List<Row> pageRows = rows.subList(start, end);

            Application.display(render(columns, pageRows));

            Application.display("\nСтраница " + currentPage + " от " + totalPages);
            Application.display("\nКоманди: next (следваща), prev (предишна), exit (изход)");
            Application.display("\n>> ");

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("exit")) {
                break;
            }
            if(!input.isEmpty()) {
                String response = cli.process(input);
                try {
                    currentPage = Integer.parseInt(response);
                }catch(NumberFormatException _){
                    Application.display(response + "\n");
                }
                prev.setCurrentPage(currentPage);
                next.setCurrentPage(currentPage);
            }

        }

        return "Изход от режима за преглед.";
    }
}



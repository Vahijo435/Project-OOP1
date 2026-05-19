package bg.tu_varna.sit.f24621646.project_oop1.io.paginationCommands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
/**
 * @author Vahan
 * Клас за команда за минаване напред по страници на дадена таблица
 *
 */
public class NextCommand implements Command {
    private int currentPage=1;
    private final int totalPages;


    /**
     * Инициализира командата с максималния брой страници.
     *
     */
    public NextCommand(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Задава текущата страница.
     *
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String execute(String[] args) {
        if (currentPage < totalPages) {
            currentPage++;
        }
        return String.valueOf(currentPage);
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDetails() {
        return "";
    }
}

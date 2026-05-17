package bg.tu_varna.sit.f24621646.project_oop1.io.paginationCommands;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Command;
/**
 * @author Vahan
 * Клас за команда за връщане назад по страници на дадена таблица
 *
 */
public class PrevCommand implements Command {
private int currentPage=1;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String execute(String[] args) {
        if (currentPage > 1) {
            currentPage--;
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

package bg.tu_varna.sit.f24621646.project_oop1.manager;

import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
/**
 * @author Vahan
 * Singleton клас, управляващ жизнения цикъл на базата данни.
 * Пази референция към текущо отворената база и пътя към нейния каталожен файл,предоставяйки глобална точка за достъп до тях.
 */
public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Database currentDatabase = null;
    private String currentCatalogFilePath = null;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean isDatabaseOpen() {
        return currentDatabase != null;
    }

    public Database getDatabase() {
        return currentDatabase;
    }

    public String getCurrentCatalogFilePath() {
        return currentCatalogFilePath;
    }

    public void setCurrentCatalogFilePath(String path) {
        this.currentCatalogFilePath = path;
    }

    public void openNewDatabase(String path) {
        this.currentDatabase = new Database();
        this.currentCatalogFilePath = path;
    }

    public void closeDatabase() {
        this.currentDatabase = null;
        this.currentCatalogFilePath = null;
    }
}
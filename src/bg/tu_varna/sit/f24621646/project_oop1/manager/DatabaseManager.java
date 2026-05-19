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

    /**
     * private конструктор за гарантиране на Singleton шаблона.
     */
    private DatabaseManager() {
    }

    /**
     * Връща глобалната инстанция на мениджъра.
     * Единственият обект на DatabaseManager.
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Проверява дали в момента има заредена база от данни.
     * връща true, ако има отворена база, false в противен случай.
     */
    public boolean isDatabaseOpen() {
        return currentDatabase != null;
    }

    /**
     * Връща обектът на текущо заредената база от данни.
     *
     */
    public Database getDatabase() {
        return currentDatabase;
    }

    /**
     * Връща пътя до каталожния файл като низ.
     *
     */
    public String getCurrentCatalogFilePath() {
        return currentCatalogFilePath;
    }
    /**
     * Задава нов път до каталожния файл.
     *
     */
    public void setCurrentCatalogFilePath(String path) {
        this.currentCatalogFilePath = path;
    }

    /**
     * Инициализира нова или съществуваща база от данни на посочения път.
     *
     */
    public void openNewDatabase(String path) {
        this.currentDatabase = new Database();
        this.currentCatalogFilePath = path;
    }
    /**
     * Затваря текущата база данни, изчиствайки референциите в паметта.
     */
    public void closeDatabase() {
        this.currentDatabase = null;
        this.currentCatalogFilePath = null;
    }
}
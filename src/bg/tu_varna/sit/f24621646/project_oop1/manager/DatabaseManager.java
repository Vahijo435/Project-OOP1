package bg.tu_varna.sit.f24621646.project_oop1.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.Value;
import bg.tu_varna.sit.f24621646.project_oop1.exceptions.DatabaseException;
import bg.tu_varna.sit.f24621646.project_oop1.models.Column;
import bg.tu_varna.sit.f24621646.project_oop1.models.DataType;
import bg.tu_varna.sit.f24621646.project_oop1.models.Database;
import bg.tu_varna.sit.f24621646.project_oop1.models.Row;
import bg.tu_varna.sit.f24621646.project_oop1.models.Table;

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
package Diginamic.TP5JPA.Utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    private static final String PERSISTENCE_UNIT_NAME = "BanquePersistenceUnit";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/banque"; // Update with your PostgreSQL database URL
    private static final String DB_USER = "postgres"; // Update with your database user
    private static final String DB_PASSWORD = "Bl@dg3r$$"; // Update with your database password
    private static final String DB_URL_CREATE = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "banque";
    private static EntityManagerFactory emf;

    // Initialize EntityManagerFactory and run Liquibase migrations
    static {
        try {
            // Check if the database exists and create it if it doesn't
            createDatabaseIfNotExists();

            // Run Liquibase migrations
            runLiquibaseMigrations();

            // Create the EntityManagerFactory
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing EntityManagerFactory", e);
        }
    }

    // Get EntityManager
    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory not initialized");
        }
        return emf.createEntityManager();
    }

    // Close EntityManagerFactory
    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }

    // Run Liquibase migrations
    private static void runLiquibaseMigrations() throws SQLException, LiquibaseException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/changelog/changelog-master.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts());
        }
    }

    // Check if the database exists, and create it if not
    private static void createDatabaseIfNotExists() throws SQLException {
        // Connect to PostgreSQL without specifying the database
        try (Connection connection = DriverManager.getConnection(DB_URL_CREATE, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();

            // Check if the database exists
            boolean databaseExists = false;
            try (var resultSet = statement.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + DB_NAME + "'")) {
                databaseExists = resultSet.next();
            }

            // Create the database if it does not exist
            if (!databaseExists) {
                statement.execute("CREATE DATABASE " + DB_NAME);
                System.out.println("Database created: " + DB_NAME);
            }
        }
    }
}

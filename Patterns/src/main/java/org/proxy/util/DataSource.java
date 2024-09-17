package org.proxy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
    private static DataSource instance;
    private final Connection connection;

    private DataSource() {
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            this.connection.setAutoCommit(false);
            createItemsTable();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    private void createItemsTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS ITEMS (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), price DOUBLE)");
        }
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

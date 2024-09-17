package org.proxy.dao;

import org.proxy.model.Item;
import org.proxy.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {
    private final Connection connection;

    public ItemsDao() {
        this.connection = DataSource.getInstance().getConnection();
    }

    public void createItems(List<Item> items) throws SQLException {
        String sql = "INSERT INTO items (title, price) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Item item : items) {
                stmt.setString(1, item.getTitle());
                stmt.setDouble(2, item.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("id"), rs.getString("title"), rs.getDouble("price"));
                items.add(item);
            }
        }
        return items;
    }

    public void updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET title = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getTitle());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
        }
    }
}

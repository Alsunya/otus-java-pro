package org.proxy.proxy;

import org.proxy.service.ItemsService;
import org.proxy.util.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {

    private final ItemsService itemsService;
    private final Connection connection;

    public ItemsServiceProxy(ItemsService itemsService) {
        this.itemsService = itemsService;
        this.connection = DataSource.getInstance().getConnection();
    }

    public void addHundredItems() throws SQLException {
        try {
            itemsService.addHundredItems();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public void doublePrices() throws SQLException {
        try {
            itemsService.doublePrices();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}

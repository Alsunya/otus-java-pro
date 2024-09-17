package org.proxy.service;

import org.proxy.Main;
import org.proxy.dao.ItemsDao;
import org.proxy.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ItemsService {
    private final static Logger logger = Logger.getLogger(Main.class.getName());
    private final ItemsDao itemsDao = new ItemsDao();

    public void addHundredItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            items.add(new Item(0, "Item " + i, 10.0 * i));
        }
        itemsDao.createItems(items);
        logger.info("100 items added");
    }

    public void doublePrices() throws SQLException {
        List<Item> items = itemsDao.getAllItems();
        for (Item item : items) {
            item.setPrice(item.getPrice() * 2);
            itemsDao.updateItem(item);
        }
        logger.info("Prices have doubled");
    }
}

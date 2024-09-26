package org.hibernate.services;

import org.hibernate.SessionFactory;
import org.hibernate.entities.Messages;
import org.hibernate.entities.Item;
import org.hibernate.utils.EntityUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class ProductService extends EntityService<Item> {
    public ProductService(SessionFactory sessionFactory) {
        super(sessionFactory, Item.class);
    }
    @Override
    public void addNew(BufferedReader reader) {
        try {
            Item item = new Item();
            logger.info(Messages.INPUT_PRODUCT_TITLE.toString());
            item.setTitle(reader.readLine());
            logger.info(Messages.INPUT_PRODUCT_COST.toString());
            item.setPrice(Double.parseDouble(reader.readLine()));
            EntityUtil.insert(sessionFactory, item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

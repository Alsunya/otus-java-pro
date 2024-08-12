package org.hibernate.services;

import org.hibernate.entities.Customer;
import org.hibernate.entities.Messages;
import org.hibernate.entities.Item;
import org.hibernate.entities.Purchase;
import org.hibernate.utils.EntityUtil;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomerService extends EntityService<Customer> {

    public CustomerService(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }
    public void addNew(BufferedReader reader) {
        try {
            Customer customer = new Customer();
            logger.info(Messages.INPUT_CUSTOMER_NAME.toString());
            customer.setName(reader.readLine());

            var tableCustomer = EntityUtil.insert(sessionFactory, customer);

            while (true) {
                logger.info(Messages.INPUT_PRODUCT_ID.toString());
                var str = reader.readLine();
                if (str.equals("!")) {
                    break;
                }
                try {
                    Item item = EntityUtil.findById(sessionFactory, Item.class, Long.parseLong(str));
                    Purchase purchase = new Purchase();
                    purchase.setCustomer(tableCustomer);
                    purchase.setItem(item);
                    purchase.setPriceAtPurchase(item.getPrice());
                    EntityUtil.insert(sessionFactory, purchase);
                }
                catch (NullPointerException e){
                    logger.warning(Messages.BAD_OPERATION.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

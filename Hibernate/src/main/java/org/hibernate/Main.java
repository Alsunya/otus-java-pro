package org.hibernate;

import org.hibernate.configurations.JavaBasedSessionFactory;
import org.hibernate.entities.Customer;
import org.hibernate.entities.Item;
import org.hibernate.services.CustomerService;
import org.hibernate.services.EntityService;
import org.hibernate.services.InputService;
import org.hibernate.services.ProductService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = JavaBasedSessionFactory.getSessionFactory()) {
            EntityService<Customer> customerService = new CustomerService(sessionFactory);
            EntityService<Item> productService = new ProductService(sessionFactory);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            InputService inputService = new InputService(customerService, productService, reader);
            inputService.start();
        }
    }
}
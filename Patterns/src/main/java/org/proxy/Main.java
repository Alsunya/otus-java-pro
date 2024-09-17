package org.proxy;

import org.proxy.proxy.ItemsServiceProxy;
import org.proxy.service.ItemsService;

import java.util.logging.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            ItemsService service = new ItemsService();
            ItemsServiceProxy serviceProxy = new ItemsServiceProxy(service);

            serviceProxy.addHundredItems();
            serviceProxy.doublePrices();
        } catch (Exception e) {
            logger.warning("Error! " + e.getMessage());
            e.printStackTrace();
        }
    }
}

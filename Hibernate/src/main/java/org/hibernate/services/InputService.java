package org.hibernate.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.entities.Customer;
import org.hibernate.entities.Messages;
import org.hibernate.entities.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class InputService {
    private final static Logger logger = Logger.getLogger(InputService.class.getName());
    private final EntityService<Customer> customerService;
    private final EntityService<Item> productService;
    private final BufferedReader reader;

    public void start() {
        try {
            while (true) {
                logger.info(Messages.HELLO_MESSAGE.toString());
                logger.info(Messages.CHOOSE_ACTION.toString());
                int input = Integer.parseInt(reader.readLine());
                switch (input) {
                    case 0:
                        return;
                    case 1:
                        productService.addNew(reader);
                        break;
                    case 2:
                        productService.printAll();
                        break;
                    case 3:
                        customerService.addNew(reader);
                        break;
                    case 4:
                        customerService.printAll();
                        break;
                    case 5:
                        customerService.findById(Long.parseLong(reader.readLine()));
                        break;
                    case 6:
                        productService.findById(Long.parseLong(reader.readLine()));
                        break;
                    case 7:
                        logger.info(Messages.INPUT_ID.toString());
                        customerService.deleteById(Long.parseLong(reader.readLine()));
                        break;
                    case 8:
                        logger.info(Messages.INPUT_ID.toString());
                        productService.deleteById(Long.parseLong(reader.readLine()));
                        break;
                    default:
                        logger.warning(Messages.BAD_OPERATION.toString());
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.hibernate;

import org.hibernate.entities.Address;
import org.hibernate.entities.Client;
import org.hibernate.entities.Phone;
import org.hibernate.utils.EntityUtil;

import java.util.Set;

import static org.hibernate.configurations.JavaBasedSessionFactory.SESSION_FACTORY;

public class App {
    public static void main(String[] args) {
        try (SESSION_FACTORY) {
            var client = createClient("Bob",
                    "88005553535",
                    "Tverskaya");

            EntityUtil.insert(SESSION_FACTORY, client);
        }
    }

    private static Client createClient(String name, String phoneNumber, String street) {
        Phone phone = new Phone();
        phone.setNumber(phoneNumber);

        Address address = new Address();
        address.setStreet(street);

        Client client = new Client();
        client.setName(name);
        client.setPhone(Set.of(phone));
        client.setAddress(address);

        return client;
    }
}

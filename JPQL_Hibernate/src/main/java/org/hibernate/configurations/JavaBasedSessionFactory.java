package org.hibernate.configurations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.entities.Address;
import org.hibernate.entities.Client;
import org.hibernate.entities.Phone;
import org.hibernate.service.ServiceRegistry;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaBasedSessionFactory {
    private static final Log LOGGER = LogFactory.getLog(MethodHandles.lookup().lookupClass());

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties properties = getProperties();

            configuration.setProperties(properties)
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Phone.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            LOGGER.info("Hibernate Java Config serviceRegistry created");

            return sessionFactory;

        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:~/test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.current_session_context_class", "thread");
        return properties;
    }
}

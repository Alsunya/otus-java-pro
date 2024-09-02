package org.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EntityUtil {
    public static <T> T insert(SessionFactory sessionFactory, T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            T merged = session.merge(entity);
            session.getTransaction().commit();

            return merged;
        }
    }
}

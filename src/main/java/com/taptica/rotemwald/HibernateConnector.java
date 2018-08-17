package com.taptica.rotemwald;

import com.taptica.rotemwald.entities.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Thread-safe Hibernate connector
 */
public class HibernateConnector {

    private static HibernateConnector instance;
    private static Object mutex = new Object();

    private SessionFactory sessionFactory;

    private HibernateConnector() {
        sessionFactory = new Configuration().configure().addAnnotatedClass(Person.class).buildSessionFactory();
    }

    public static HibernateConnector getInstance() {
        HibernateConnector result = instance;

        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new HibernateConnector();
            }
        }

        return result;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

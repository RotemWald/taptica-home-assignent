package com.taptica.rotemwald;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector {

    private static HibernateConnector instance = null;

    private SessionFactory sessionFactory;

    private HibernateConnector() {
        sessionFactory = new Configuration().configure().addAnnotatedClass(Person.class).buildSessionFactory();
    }

    public static HibernateConnector getInstance() {
        if (instance == null) {
            instance  = new HibernateConnector();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

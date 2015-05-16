package com.ak.rstore.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ORMUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session currentSession() throws HibernateException {
        Session s = sessionThreadLocal.get();
        if (s == null) {
            s = sessionFactory.openSession();
            sessionThreadLocal.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = sessionThreadLocal.get();
        sessionThreadLocal.set(null);
        if (s != null) {
            s.close();
        }
    }
}





























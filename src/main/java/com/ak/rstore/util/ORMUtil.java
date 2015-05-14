package com.ak.rstore.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ORMUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    private static SessionFactory buildSessionFactory() {
        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            return new Configuration().configure().buildSessionFactory(
//                    new StandardServiceRegistryBuilder().build() );
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session currentSession() throws HibernateException {
        Session s = session.get();
        if(s==null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = session.get();
        session.set(null);
        if (s != null) {
            s.close();
        }
    }


//    public Session getSession() {
//        if(session == null) {
//            session = sessionFactory.openSession();
//        }
//        return session;
//    }
//
//    public void close() {
//        sessionFactory.close();
//    }

}

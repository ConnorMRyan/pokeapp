package com.nigiri.pokeapp.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateService {
    private static Session session;
    private static final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    private static final SessionFactory sf = configuration.buildSessionFactory();

    public static Session getSession() {
        if(session == null){
            session = sf.openSession();
        }
        return session;
    }

    public static void closeSession(){
        session.close();
        session = null;
    }
}
package com.nigiri.pokeapp.Utils;

import com.nigiri.pokeapp.MonsterStuff.MonsterDAO;
import org.hibernate.Session;

public class MonsterService {
    void saveMonster(MonsterDAO monster) throws Exception {
        Session session = HibernateService.getSession();
        session.beginTransaction();
        session.save(monster);
        session.getTransaction().commit();

    }

    public MonsterDAO getMonster(int id){
        Session session = HibernateService.getSession();
        MonsterDAO newmon = session.get(MonsterDAO.class,id);
        newmon.setID(id);
        return newmon;
    }
}

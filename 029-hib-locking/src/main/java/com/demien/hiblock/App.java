package com.demien.hiblock;

import com.demien.hiblock.db.HibernateUtil;
import com.demien.hiblock.dto.User;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        User user = new User();
        //user.setUserId(2L);
        //user.setUserName("Huan Sebastyan");

        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        session.persist(user);

        System.out.println(user);

        session.getTransaction().commit();
        session.close();


    }
}

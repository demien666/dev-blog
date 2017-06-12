package com.demien.richdomain.model;

import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dmitry on 14.09.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class AbstractModelTest {

    @Test
    public void simpleTest() {
        User user=new User("login", "password");
        int cnt0=user.getAll().size();

        Integer id=user.save();

        int cnt1=user.getAll().size();
        Assert.assertEquals(cnt0+1, cnt1);

        User user1=user.get(id);
        Assert.assertEquals(user, user1);

        user.setLogin("login_updated");
        user.update();

        user1.get(id);
        Assert.assertEquals("login_updated", user1.getLogin());
    }

    @Test
    public void transactionRollbackTest() {
        User user1=new User("login1","password1");
        User user2=new User("login2","password2");
        int cnt0=user1.getAll().size();

        user1.setAutoCommit(false);
        user2.setAutoCommit(false);

        Session session=user1.getNewSession();
        user1.setSession(session);
        user2.setSession(session);

        session.beginTransaction();
        user1.save();
        user2.save();
        session.getTransaction().rollback();

        int cnt1=user1.getAll().size();
        Assert.assertEquals(cnt0, cnt1);

    }

    @Test
    public void transactionCommitTest() {
        User user1=new User("login1","password1");
        User user2=new User("login2","password2");
        int cnt0=user1.getAll().size();

        user1.setAutoCommit(false);
        user2.setAutoCommit(false);

        Session session=user1.getNewSession();
        user1.setSession(session);
        user2.setSession(session);

        session.beginTransaction();
        user1.save();
        user2.save();
        session.getTransaction().commit();

        int cnt1=user1.getAll().size();
        Assert.assertEquals(cnt0+2, cnt1);

    }

}

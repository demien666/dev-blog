package com.demien.hiblock

import com.demien.hiblock.db.HibernateUtil
import com.demien.hiblock.dto.User
import org.hibernate.LockMode
import org.hibernate.LockOptions
import org.hibernate.Session
import spock.lang.Specification

class AppTest extends Specification {

    def Session session = HibernateUtil.getSession()

    def setupSpec() {
        println "Starting"
    }

    def cleanupSpec() {
        println "Done!"
    }

    def setup() {
        session.beginTransaction()
        session.createQuery("delete from User").executeUpdate()
        session.getTransaction().commit()
        session.beginTransaction()
    }

    def cleanup() {
        session.clear()
    }

    def updateUser(id, name) {
        def session = HibernateUtil.getSession()
        def tx = session.getTransaction()
        tx.begin()
        def user = session.get(User, id)
        user.setUserName(name)
        session.update(user)
        tx.commit()
    }

    def "it shoud create User entity"() {
        given:
        session.persist(new User(1l, "Huan Sebastyan"))
        session.getTransaction().commit()

        when:
        User loadedUser = session.get(User.class, 1L)

        then:
        loadedUser.getUserName() == "Huan Sebastyan"
    }

    def "it should fail if record was changed"() {
        given:
        session.persist(new User(1l, "Huan Sebastyan"))
        session.getTransaction().commit()

        when:
        User loadedUser1 = session.get(User.class, 1L, new LockOptions(LockMode.OPTIMISTIC))
        updateUser(1L, "Updated2")

        loadedUser1.setUserName("Updated1")

        session.update(loadedUser1)

        then:
        int i = 1


    }


}

package com.demien.hiblock

import com.demien.hiblock.db.HibernateUtil
import com.demien.hiblock.dto.User
import org.hibernate.LockMode
import org.hibernate.LockOptions
import org.hibernate.Session
import spock.lang.Specification

import javax.persistence.OptimisticLockException

class AppTest extends Specification {

    def Session session = HibernateUtil.getSession()

    def setupSpec() {
        println "Starting"
    }

    def cleanupSpec() {
        println "Done!"
    }

    def setup() {
        doInTransaction({
            session.createQuery("delete from User").executeUpdate()
        })
    }

    def cleanup() {
        session.clear()
    }

    def doInTransaction(f) {
        def tx = session.getTransaction()
        tx.begin()
        f()
        tx.commit()
    }

    def createEntity(entity) {
        doInTransaction({
            session.persist(entity)
        })
    }

    def updateEntity(entity) {
        doInTransaction({
            session.update(entity)
        })
    }

    def updateEntityInAnotherSession(entity) {
        def oldSession = session
        session = HibernateUtil.getSession()
        updateEntity(entity)
        session = oldSession
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
        createEntity(new User(1l, "Huan Sebastyan"))

        when:
        User loadedUser = session.get(User.class, 1L)

        then:
        loadedUser.getUserName() == "Huan Sebastyan"
    }


    def "it should fail if record was changed by another session"() {
        given:
        createEntity(new User(1l, "Huan Sebastyan"))
        User loadedUser1 = session.get(User.class, 1L, new LockOptions(LockMode.OPTIMISTIC))
        User loadedUser2 = session.get(User.class, 1L)
        loadedUser1.setUserName("Updated1")
        loadedUser2.setUserName("Updated2")

        when:
        updateEntityInAnotherSession(loadedUser1)
        updateEntity(loadedUser2)

        then:
        thrown OptimisticLockException

    }


}

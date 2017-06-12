package com.demien.hiblock

import com.demien.hiblock.db.HibernateUtil
import com.demien.hiblock.dto.Group
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
            session.createQuery("delete from Group").executeUpdate()
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

    def "it should fail if the same fields were changed"() {
        given:
        createEntity(new Group(1L, "SYSDBA", "Database Administrators"))
        Group loadedGroup1 = session.get(Group.class, 1L)
        Group loadedGroup2 = session.get(Group.class, 1L)
        loadedGroup1.setGroupName("Updated1")
        loadedGroup2.setGroupName("Updated2")

        when:
        updateEntityInAnotherSession(loadedGroup1)
        updateEntity(loadedGroup2)

        then:
        thrown OptimisticLockException


    }


}

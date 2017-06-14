package com.demien.hiblock

import com.demien.hiblock.db.HibernateUtil
import com.demien.hiblock.dto.User
import com.demien.hiblock.dto.UserGroup
import org.hibernate.Session
import spock.lang.Specification

/**
 * Created by dmytro.kovalskyi on 6/13/2017.
 */
class BaseTest extends Specification {
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
            session.createQuery("delete from UserGroup").executeUpdate()
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

    def createTestUser() {
        createEntity(new User(1l, "Huan Sebastyan"))
    }

    def loadTestUser() {
        return session.get(User.class, 1L)
    }

    def createTestGroup() {
        createEntity(new UserGroup(1L, "SYSDBA", "Database Administrators"))
    }

    def loadTestGroup() {
        return session.get(UserGroup.class, 1L)
    }

}

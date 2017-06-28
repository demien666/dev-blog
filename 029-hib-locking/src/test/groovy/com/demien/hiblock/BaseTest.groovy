package com.demien.hiblock

import com.demien.hiblock.db.HibernateUtil
import com.demien.hiblock.dto.Role
import com.demien.hiblock.dto.User
import com.demien.hiblock.dto.UserGroup
import org.hibernate.LockMode
import org.hibernate.LockOptions
import org.hibernate.resource.transaction.spi.TransactionStatus
import spock.lang.Specification

abstract class BaseTest extends Specification {
    def session = HibernateUtil.getSession()

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
            session.createQuery("delete from Role").executeUpdate()
        })
    }

    def cleanup() {
        session.clear()
    }

    def doInTransaction(f) {
        def tx = session.getTransaction()
        if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
            tx.commit()
        }
        tx.begin()
        f()
        tx.commit()
    }

    def doInAnotherSession(f) {
        def oldSession = session
        session = HibernateUtil.getSession()
        f()
        session = oldSession
    }

    def doAsync(f) {
        def runnale = new Runnable() {
            @Override
            void run() {
                f()
            }
        }
        def thread = new Thread(runnale)
        thread.start()

    }

    def createEntity(entity) {
        doInTransaction({
            session.persist(entity)
        })
    }

    def updateEntity(entity) {
        doInTransaction({
            session.merge(entity)
        })
    }

    def mergeEntity(entity) {
        doInTransaction({
            session.merge(entity)
        })
    }

    def updateEntityInAnotherSession(entity) {
        doInAnotherSession({ updateEntity(entity) })
    }

    def lockEntity(entity) {
        session.buildLockRequest(new LockOptions(LockMode.PESSIMISTIC_WRITE)).lock(entity)
    }

    def lockEntityInAnotherSession(entity) {
        doInAnotherSession({ lockEntity(entity) })
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

    def createTestRole() {
        createEntity(new Role(1L, "SYSDBA", "Admin role"))
    }

    def loadTestRole() {
        return session.get(Role.class, 1L)
    }

}

package com.demien.hiblock

import com.demien.hiblock.dto.User

import javax.persistence.OptimisticLockException

class OptimiticVersionLockingTest extends BaseTest {

    def "it should succeed if record was changed in current session"() {
        given:
        createTestUser()

        User loadedUser1 = loadTestUser()
        User loadedUser2 = loadTestUser()

        loadedUser1.setUserName("Updated1")
        loadedUser2.setUserName("Updated2")

        when:
        updateEntity(loadedUser1)
        updateEntity(loadedUser2)
        User loadedUser3 = loadTestUser()

        then:
        loadedUser3.getUserName() == "Updated2"
    }

    def "it should fail if record was changed by another session"() {
        given:
        createTestUser()
        User loadedUser1 = loadTestUser()
        User loadedUser2 = loadTestUser()
        loadedUser1.setUserName("Updated1")
        loadedUser2.setUserName("Updated2")

        when:
        updateEntityInAnotherSession(loadedUser1)
        updateEntity(loadedUser2)

        then:
        thrown OptimisticLockException

    }

}

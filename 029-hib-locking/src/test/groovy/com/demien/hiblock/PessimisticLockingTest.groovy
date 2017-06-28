package com.demien.hiblock

import com.demien.hiblock.dto.Role

import javax.persistence.LockTimeoutException

class PessimisticLockingTest extends BaseTest {

    def "it should throw the exception on trying to update locked record"() {
        given:
        createTestRole()
        Role role1 = loadTestRole()

        lockEntity(role1)

        when:
        doInAnotherSession({

            Role role2 = loadTestRole()
            role2.setRoleDescription("Updated description")
            mergeEntity(role2)

        })

        then:
        thrown LockTimeoutException

    }
}

package com.demien.hiblock

import com.demien.hiblock.dto.Role

class PessimisticLockingTest extends BaseTest {

    def "it should throw the exception"() {
        given:
        createTestRole()
        Role role1 = loadTestRole()
        lockEntity(role1)
        role1.setRoleDescription("Updated description")

        when:
        Role role2 = loadTestRole()
        lockEntityInAnotherSession(role2)

        role2.setRoleName("Updated name")
        updateEntityInAnotherSession(role2)

        updateEntity(role1)

        then:
        System.out.println("done")

    }
}

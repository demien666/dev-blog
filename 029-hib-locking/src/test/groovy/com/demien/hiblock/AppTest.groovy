package com.demien.hiblock

import com.demien.hiblock.dto.User
import com.demien.hiblock.dto.UserGroup

import javax.persistence.OptimisticLockException

class AppTest extends BaseTest {

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
        User loadedUser1 = session.get(User.class, 1L)
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
        createEntity(new UserGroup(1L, "SYSDBA", "Database Administrators"))
        UserGroup loadedGroup1 = session.get(UserGroup.class, 1L)
        UserGroup loadedGroup2 = session.get(UserGroup.class, 1L)
        loadedGroup1.setGroupName("Updated1")
        loadedGroup2.setGroupName("Updated2")

        when:
        updateEntityInAnotherSession(loadedGroup1)
        updateEntity(loadedGroup2)

        then:
        thrown OptimisticLockException
    }


}

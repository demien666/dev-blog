package com.demien.hiblock

import com.demien.hiblock.dto.UserGroup

import javax.persistence.OptimisticLockException

class OptimisticAnnotationLockingTest extends BaseTest {


    def "it should succeed if updated in one session"() {
        given:
        createTestGroup()

        UserGroup loadedGroup1 = loadTestGroup()
        UserGroup loadedGroup2 = loadTestGroup()

        loadedGroup1.setGroupName("Updated name")
        loadedGroup2.setGroupDescription("Updated description")

        when:
        updateEntity(loadedGroup1)
        updateEntity(loadedGroup2)
        // update UserGroup set groupDescription=?, groupName=? where groupId=? and groupDescription=? and groupName=?
        // just one update statement for 2 changes

        then:
        UserGroup loadedGroup3 = loadTestGroup()
        loadedGroup3.getGroupName() == "Updated name"
        loadedGroup3.getGroupDescription() == "Updated description"
    }


    def "it should fail if record was changed by another session"() {
        given:
        createTestGroup()
        UserGroup loadedGroup1 = loadTestGroup()
        UserGroup loadedGroup2 = loadTestGroup()
        loadedGroup1.setGroupName("Updated name")
        loadedGroup2.setGroupDescription("Updated description")


        when:
        updateEntityInAnotherSession(loadedGroup1)
        //update UserGroup set groupDescription=?, groupName=? where groupId=?
        //update goes by groupId, because another session has no idea in changes of loadedGroup1 and loadedGroup2

        updateEntity(loadedGroup2)
        //update UserGroup set groupDescription=?, groupName=? where groupId=? and groupDescription=? and groupName=?
        //update goes by groupId=? and groupDescription=? and groupName=? and it should fail

        then:
        thrown OptimisticLockException
    }

}

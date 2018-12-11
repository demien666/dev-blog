package com.demien.springtest;

import com.demien.springtest.domain.Group;
import com.demien.springtest.service.PermissionServiceImplRefactored;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PermissionServiceRefactoredTest {

    @Test
    public void containsTest() {
        PermissionServiceImplRefactored refactored = new PermissionServiceImplRefactored();
        List<Group> testGroups = Arrays.asList(
                new Group("first"),
                new Group("second")
        );
        Assert.assertTrue(refactored.contains(testGroups, "first"));
        Assert.assertFalse(refactored.contains(testGroups, "trash"));
    }

}

package com.demien.springtest.service;

import com.demien.springtest.domain.Group;
import com.demien.springtest.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Repository
@Qualifier("UserGroupService-main")
public class UserGroupServiceImpl implements UserGroupService {

    @Override
    public List<Group> getUserGroups(User user) {
      log.info("It should be DB call here");
        return Arrays.asList(
                new Group("first"),
                new Group("second")
        );
    }
}

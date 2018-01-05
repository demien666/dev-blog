package com.demien.ddd.group.infrastructure;

import com.demien.ddd.application.base.BaseService;
import com.demien.ddd.application.annotations.DDDService;
import com.demien.ddd.group.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DDDService
@Component
public class GroupService extends BaseService<Group> {

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        super(groupRepository);
    }
}

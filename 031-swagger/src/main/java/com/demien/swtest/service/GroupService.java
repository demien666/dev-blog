package com.demien.swtest.service;

import com.demien.swtest.model.Group;
import org.springframework.stereotype.Service;

import java.util.function.UnaryOperator;

@Service
public class GroupService extends AbstractService<Group> {

    public GroupService() {
        super((e, id) -> {
            e.setId(id);
            return e;
        });
    }
}

package com.demien.ddd.group.infrastructure;

import com.demien.ddd.application.base.BaseRepository;
import com.demien.ddd.application.annotations.DDDRepository;
import com.demien.ddd.group.domain.Group;
import org.springframework.stereotype.Component;

@DDDRepository
@Component
public class GroupRepository extends BaseRepository<Group> {
}

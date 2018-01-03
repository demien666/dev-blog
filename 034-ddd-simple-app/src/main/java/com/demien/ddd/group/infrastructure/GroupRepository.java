package com.demien.ddd.group.infrastructure;

import com.demien.ddd.base.BaseRepository;
import com.demien.ddd.base.annotations.DDDRepository;
import com.demien.ddd.group.domain.Group;
import org.springframework.stereotype.Component;

@DDDRepository
@Component
public class GroupRepository extends BaseRepository<Group> {
}

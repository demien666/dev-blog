package com.demien.swtest.model;

import com.demien.swtest.dto.GroupDTO;
import io.swagger.annotations.ApiModelProperty;

public class Group extends GroupDTO{
    @ApiModelProperty(notes = "Generated Group ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group() {
    }

    public Group(GroupDTO dto) {
        setName(dto.getName());
    }

}

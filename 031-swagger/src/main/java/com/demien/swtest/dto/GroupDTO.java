package com.demien.swtest.dto;

import io.swagger.annotations.ApiModelProperty;

public class GroupDTO {

    @ApiModelProperty(notes = "Group Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

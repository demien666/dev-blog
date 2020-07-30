package com.demien.spbootkafkaproducer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TestEntity {
    private String id;
    private String name;
    private String description;    
}
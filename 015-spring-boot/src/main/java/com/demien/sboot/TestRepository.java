package com.demien.sboot;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestRepository {
    private static List<TestEntity> storage=new ArrayList<TestEntity>();


    public TestEntity add(TestEntity entity) {
        entity.setEntityId(storage.size()+1);
        storage.add(entity);
        return entity;
    }

    public TestEntity getById(Long entityId) {
        return storage.get(  entityId.intValue()-1);
    }

    public int getMaxId(){
        return storage.size();
    }

    public List<TestEntity> getAll() {
        return storage;
    }



}

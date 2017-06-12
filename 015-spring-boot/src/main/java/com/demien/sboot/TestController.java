package com.demien.sboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entity")
public class TestController {

    @Autowired
    TestRepository repository;

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, world!";
    }

    @RequestMapping(value = "add",  method = RequestMethod.POST)
    public @ResponseBody
    TestEntity add(@RequestBody TestEntity entity) {
        TestEntity result=repository.add(entity);
        return result;
    }

    @RequestMapping(value="get/{entityId}", method = RequestMethod.GET)
    public TestEntity get(@PathVariable Long entityId) {
        return repository.getById(entityId);
    }

    @RequestMapping(value="getall", method = RequestMethod.GET)
    public List<TestEntity> getAll() {
        return repository.getAll();
    }

}

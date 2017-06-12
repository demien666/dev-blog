package com.demien.sparktest.controller;

import com.demien.sparktest.util.JsonUtil;
import com.demien.sparktest.domain.IPersistable;
import com.demien.sparktest.service.GenericService;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class GenericController<T extends IPersistable> {
    private GenericService<T> service;
    private Class<T> cl;

    public GenericController(String basePath, Class<T> cl, GenericService<T> service) {
        this.cl=cl;
        this.service=service;
        Spark.get(basePath,this::getAll, JsonUtil::toJson);
        Spark.get(basePath+"/:id",this::getById, JsonUtil::toJson);
        Spark.get(basePath+"/test",this::test, JsonUtil::toJson);
        Spark.post(basePath,this::add, JsonUtil::toJson);
        Spark.put(basePath,this::update, JsonUtil::toJson);
        Spark.delete(basePath,this::delete, JsonUtil::toJson);
    }

    public Object test(Request request, Response response) {
        return "Hello world!";
    }

    public Object getAll(Request request, Response response) {
        return service.getAll();
    }

    public Object getById(Request request, Response response) {
        String id = request.params(":id");
        return service.getById(Long.parseLong(id));
    }

    public T restoreObjectFromRequest(Request request) {
        return (T)JsonUtil.toObject(request.body(),cl);
    }

    public Object add(Request request, Response response) {
        return service.add(restoreObjectFromRequest(request));
    }

    public T update(Request request, Response response) {
        T entity=(T)restoreObjectFromRequest(request);
        return service.update(entity);
    }

    public Object delete(Request request, Response response) {
        service.delete(restoreObjectFromRequest(request));
        return "";
     }




}

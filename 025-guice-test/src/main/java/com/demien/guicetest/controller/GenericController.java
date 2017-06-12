package com.demien.guicetest.controller;

import com.demien.guicetest.service.GenericService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Spark;

public abstract class GenericController<T> {

    protected GenericService service;

    public GenericController(String basePath, GenericService service) {
        this.service=service;
        Spark.get(basePath, this::getAll, this::toJson);
        Spark.get(basePath+"/:id",this::getById, this::toJson);
        Spark.get(basePath+"/test",this::test, this::toJson);
        Spark.post(basePath,this::persist, this::toJson);
        Spark.put(basePath,this::persist, this::toJson);
        Spark.delete(basePath,this::remove, this::toJson);
    }

    public Object toObject(String json, Class<?> cl) {
        return new Gson().fromJson(json, cl);
    }

    public String toJson(Object object) {
        return new Gson().toJson(object);
    }


    public Object restoreObjectFromRequest(Request request) {
        return toObject(request.body(), service.getEntityClass());
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


    public Object persist(Request request, Response response) {
        return service.persist(restoreObjectFromRequest(request));
    }

    public Object remove(Request request, Response response) {
        service.remove(restoreObjectFromRequest(request));
        return "";
    }

}

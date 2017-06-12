package com.demien.springws.service;

import com.demien.springws.domain.Category;
import com.demien.springws.domain.operation.CategoryGetByIdRequest;
import com.demien.springws.domain.operation.CategoryGetByIdResponse;
import com.demien.springws.domain.operation.CategorySaveRequest;
import com.demien.springws.domain.operation.CategorySaveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CategoryServiceEndpoint {
    private static final String TARGET_NAMESPACE = "http://com/demien/springws/domain/operation";


    @Autowired
    CategoryService categoryService;

    public CategoryServiceEndpoint() {
    }

    @PayloadRoot(localPart = "CategorySaveRequest", namespace = TARGET_NAMESPACE)
    public @ResponsePayload
    CategorySaveResponse save(@RequestPayload CategorySaveRequest request)       {
        CategorySaveResponse response = new CategorySaveResponse();
        Category category=categoryService.save(request.getCategory());
        response.setCategory(category);
        return response;
     }

    @PayloadRoot(localPart = "CategoryGetByIdRequest", namespace = TARGET_NAMESPACE)
    public @ResponsePayload
    CategoryGetByIdResponse save(@RequestPayload CategoryGetByIdRequest request)       {
        CategoryGetByIdResponse response = new CategoryGetByIdResponse();
        Category category=categoryService.getById(request.getCategoryId());
        response.setCategory(category);
        return response;
    }

}

package com.demien.guicetest.controller;

import com.demien.guicetest.App;
import com.demien.guicetest.service.CategoryService;

import javax.inject.Inject;

public class CategoryController extends GenericController {

    @Inject
    public CategoryController(CategoryService service) {
        super(App.PATH_CATEGORY, service);

    }

}

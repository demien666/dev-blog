package com.demien.guicetest.service;


import com.demien.guicetest.domain.Category;

import javax.inject.Singleton;

@Singleton
public class CategoryService extends GenericService<Category, Long> {
    public CategoryService() {
        super(Category.class);
    }
}

package com.demien.springws.service;

import com.demien.springws.domain.Category;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryService {

    private Map<BigInteger,Category> storage=new HashMap<BigInteger,Category>();
    private int id=1;

    public Category save(Category category) {
        category.setCategoryId(BigInteger.valueOf(id));
        id++;
        storage.put(category.getCategoryId(), category);
        return category;
    }

    public Category getById(BigInteger categoryId) {
        Category result=storage.get(categoryId);
        return result;
    }
}

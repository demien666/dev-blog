package com.demien.sboot.rest;

import com.demien.sboot.domain.Category;
import com.demien.sboot.service.AbstractService;
import com.demien.sboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category, Long> {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        super(service);
        this.service=service;
    }

    @RequestMapping(value="findByName/{name}", method = RequestMethod.GET)
    public List<Category> findByName(@PathVariable String name) {
        return service.findByName(name);
    }
}

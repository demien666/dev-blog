package com.demien.guicetest;

import com.demien.guicetest.controller.CategoryController;
import com.demien.guicetest.domain.Category;
import com.demien.guicetest.service.CategoryService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import spark.Spark;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App extends AbstractModule {
    public final static int SPARK_PORT=8080;

    public final static String PATH_CATEGORY="/category";
    public final static String PATH_PARAM="/param";

    private static final String PERSISTENCE_UNIT_NAME = "work";
    private static EntityManagerFactory factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;

    public static void main(String[] args) {
        Spark.setPort(SPARK_PORT);
        Injector injector=Guice.createInjector(new App());
        injector.getInstance(CategoryController.class);

        CategoryService categoryService=injector.getInstance(CategoryService.class);
        Category testCategory=new Category();
        testCategory.setCategoryId(1L);
        testCategory.setCategoryName("Test Category");
        categoryService.persist(testCategory);

    }

    @Override
    protected void configure() {

    }

    @Provides
    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    @Provides
    public EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }


}

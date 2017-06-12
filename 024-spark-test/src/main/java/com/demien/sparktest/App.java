package com.demien.sparktest;

import com.demien.sparktest.controller.GenericController;
import com.demien.sparktest.domain.Param;
import com.demien.sparktest.service.GenericService;
import spark.Spark;
import com.demien.sparktest.domain.Item;

public class App {

    public final static int SPARK_PORT=8080;
    public final static String APP_PATH="http://localhost:"+SPARK_PORT;

    public final static GenericService<Item> itemService=new GenericService<>();
    public final static String ITEM_PATH="/item";

    public final static GenericService<Param> paramService=new GenericService<>();
    public final static String PARAM_PATH="/param";

    public static void main(String[] args) {
        Spark.setPort(8080);
        GenericController<Item> itemController=new GenericController<Item>(ITEM_PATH, Item.class, itemService);
        GenericController<Param> paramController=new GenericController<Param>(PARAM_PATH, Param.class, paramService);
    }

}

package com.demien.sparktest;

import com.demien.sparktest.domain.Item;

public class ItemControllerIT extends GenericControllerIT<Item> {
    public ItemControllerIT() {
        super(App.ITEM_PATH, Item.class, App.itemService);
    }
}

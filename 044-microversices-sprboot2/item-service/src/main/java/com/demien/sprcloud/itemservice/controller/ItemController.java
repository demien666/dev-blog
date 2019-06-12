package com.demien.sprcloud.itemservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demien.sprcloud.itemservice.domain.Item;
import com.demien.sprcloud.itemservice.repository.ItemRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{itemId}")
    public Item getById(@PathVariable String itemId) {
        return itemRepository.findById(itemId).orElseGet( () -> new Item());
    }


}

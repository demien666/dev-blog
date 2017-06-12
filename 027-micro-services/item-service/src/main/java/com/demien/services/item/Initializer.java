package com.demien.services.item;

import com.demien.services.item.domain.Item;
import com.demien.services.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... strings) throws Exception {
        itemRepository.save(new Item("I6S", "Iphone 6s", new BigDecimal(400)));
        itemRepository.save(new Item("I7", "Iphone 7", new BigDecimal(500)));
        itemRepository.save(new Item("N5", "Samsung galaxy note 5", new BigDecimal(450)));
    }
}

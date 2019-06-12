package com.demien.sprcloud.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demien.sprcloud.cartservice.domain.CartItem;
import com.demien.sprcloud.cartservice.repository.CartItemRepository;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void run(String... strings) throws Exception {
        cartItemRepository.addCardItemByToken("t1", new CartItem("I6S", 1));
        cartItemRepository.addCardItemByToken("t2", new CartItem("I7", 2));
        cartItemRepository.addCardItemByToken("t3", new CartItem("N5", 3));
    }
}

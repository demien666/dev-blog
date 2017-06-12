package com.demien.services.cart;

import com.demien.services.cart.domain.CartItem;
import com.demien.services.cart.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

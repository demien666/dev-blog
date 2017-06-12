package com.demien.services.cart.repository;

import com.demien.services.cart.domain.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartItemRepository {

    private final static Map<String, ArrayList<CartItem>> STORAGE=new HashMap<>();

    public void addCardItemByToken(String tokenId, CartItem cartItem) {
        ArrayList<CartItem> cartItems =STORAGE.get(tokenId);
        if (cartItems ==null) {
            cartItems = new ArrayList<CartItem>();
        }
        cartItems.add(cartItem);
        STORAGE.put(tokenId, cartItems);
    }

    public List<CartItem> getCardItemsByToken(String tokenId) {
        return STORAGE.get(tokenId);
    }

}

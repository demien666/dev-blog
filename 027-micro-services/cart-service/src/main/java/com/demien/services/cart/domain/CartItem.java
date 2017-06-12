package com.demien.services.cart.domain;

import java.math.BigDecimal;

public class CartItem {
    private String itemId;
    private int amount;

    public CartItem() {
    }

    public CartItem(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

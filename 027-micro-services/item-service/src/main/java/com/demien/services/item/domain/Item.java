package com.demien.services.item.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Item {
    @Id
    private String itemId;
    private String itemName;
    private BigDecimal price;

    public Item() {
    }

    public Item(String itemId,String itemName, BigDecimal price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}

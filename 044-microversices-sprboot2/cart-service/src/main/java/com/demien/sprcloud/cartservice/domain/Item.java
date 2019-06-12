package com.demien.sprcloud.cartservice.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {

	private String itemId;
	private String itemName;
	private BigDecimal price;

}

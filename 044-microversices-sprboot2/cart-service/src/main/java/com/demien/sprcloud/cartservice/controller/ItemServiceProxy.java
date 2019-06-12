package com.demien.sprcloud.cartservice.controller;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demien.sprcloud.cartservice.domain.Item;

@FeignClient(contextId = "itemClient", name = "edge-server")
@RibbonClient(name = "item-service")
public interface ItemServiceProxy {

	@RequestMapping(value = "/item-service/item/{itemId}", method = RequestMethod.GET)
	public Item getById(@PathVariable("itemId") String itemId);

	@RequestMapping(value = "/item-service/item/getAll", method = RequestMethod.GET)
	public List<Item> getAll();

}

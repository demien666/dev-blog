package com.demien.sprcloud.cartservice.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demien.sprcloud.cartservice.domain.CartItem;
import com.demien.sprcloud.cartservice.domain.Item;
import com.demien.sprcloud.cartservice.repository.CartItemRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableDiscoveryClient
@RestController
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserServiceProxy userServiceProxy;

	@Autowired
	private ItemServiceProxy itemServiceProxy;

	public String getDefaultResponse() {
		return "Something is wrong. Please try again later";

	}

	@HystrixCommand(fallbackMethod = "getDefaultResponse")
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String test() {
		final StringBuilder result = new StringBuilder();
		result.append("Logging in into userService as user1/pasword <br/> ");
		final String tokenId = this.userServiceProxy.login("user1", "password1");
		result.append("Received Token: " + tokenId + "<br/><br/>");
		result.append("Getting user details from userService by token <br/>");
		final String userDetails = this.userServiceProxy.userByToken(tokenId);
		result.append("Reseived UserDetails: " + userDetails + "<br/><br/>");

		result.append("Getting item list from itmService <br/>");
		final List<Item> items = this.itemServiceProxy.getAll();
		result.append("Reseived items: <br/>");
		items.forEach(item -> result.append("    " + item.toString() + " <br/>"));

		return result.toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/testLogin")
	public String testLogin() {
		return this.userServiceProxy.login("user1", "password1");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/userByToken/{tokenId}")
	public String testUserByToken(@PathVariable("tokenId") final String tokenId) {
		return this.userServiceProxy.userByToken(tokenId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/itemById/{itemId}")
	public Item getItemById(@PathVariable("itemId") final String itemId) {
		return this.itemServiceProxy.getById(itemId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{tokenId}")
	public List<CartItem> getCardItemsByToken(@PathParam("tokenId") final String tokenId) {
		return this.cartItemRepository.getCardItemsByToken(tokenId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addUserItem(@RequestParam final String tokenId, @RequestParam final String itemId,
			@RequestParam final String amount) {
		final CartItem cartItem = new CartItem(itemId, Integer.parseInt(amount));
		this.cartItemRepository.addCardItemByToken(tokenId, cartItem);
	}






	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String getCartOrderByToken(@RequestParam("tokenId") final String tokenId) {
		if (tokenId == null) {
			throw new RuntimeException("TokenId is null");
		}
		final StringBuilder result = new StringBuilder();
		//final Object userResponse = getUserDetailsByToken(tokenId);/*
		final String userDetails = this.userServiceProxy.userByToken(tokenId);

		result.append("User:" + userDetails + "\n");

		final List<CartItem> cartItems = this.cartItemRepository.getCardItemsByToken(tokenId);
		BigDecimal total = BigDecimal.ZERO;
		int index = 0;
		for (final CartItem cartItem : cartItems) {
			index++;
			final Item item = getItemById(cartItem.getItemId());

			final BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(cartItem.getAmount()));
			total = total.add(itemTotal);
			result.append("  " + index + ". item:" + item.getItemName() + ", price:" + item.getPrice() + ", amount:"
					+ cartItem.getAmount()
					+ ", itemTotal:" + itemTotal + "\n");
		}
		result.append("Total:" + total);

		return result.toString();
	}




}

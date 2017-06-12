package com.demien.services.cart.controller;

import com.demien.services.cart.domain.CartItem;
import com.demien.services.cart.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

@EnableDiscoveryClient
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    public final static String ITEM_SERVICE_PATH="http://item-service/item";
    public final static String USER_SERVICE_PATH="http://user-service/user";
    public final static String USER_BY_TOKEN=USER_SERVICE_PATH+"/byToken";

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(method = RequestMethod.GET, value="/{tokenId}")
    public List<CartItem> getCardItemsByToken(@PathParam("tokenId") String tokenId) {
        return cartItemRepository.getCardItemsByToken(tokenId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUserItem(@RequestParam String tokenId, @RequestParam String itemId, @RequestParam String amount) {
        CartItem cartItem =new CartItem(itemId, Integer.parseInt(amount));
        cartItemRepository.addCardItemByToken(tokenId, cartItem);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String getCartOrderByToken(@RequestParam("tokenId") String tokenId) {
        if (tokenId == null) {
            throw new RuntimeException("TokenId is null");
        }
        StringBuilder result=new StringBuilder();
        Object userResponse = getUserDetailsByToken(tokenId);
        String userName = (String)getValueFromResponse(userResponse, "name");
        String userAddress = (String)getValueFromResponse(userResponse, "address");

        result.append("User:"+userName+"\n");
        result.append("Address:"+userAddress+"\n");

        List<CartItem> cartItems = cartItemRepository.getCardItemsByToken(tokenId);
        BigDecimal total=BigDecimal.ZERO;
        int index=0;
        for (CartItem cartItem:cartItems) {
            index++;
            Object itemResponse = getItemDetails(cartItem.getItemId());
            String itemName = (String)getValueFromResponse(itemResponse, "itemName");
            BigDecimal price = new BigDecimal( (Double) getValueFromResponse(itemResponse, "price"));
            BigDecimal itemTotal = price.multiply(new BigDecimal(cartItem.getAmount()));
            total = total.add(itemTotal);
            result.append("  "+index+". item:"+itemName+", price:"+price+", amount:"+cartItem.getAmount()+", itemTotal:"+itemTotal +"\n");
        }
        result.append("Total:"+total);

        return result.toString();
    }

    public Object getValueFromResponse(Object response, String value) {
        return ((LinkedHashMap)response).get(value);

    }

    public Object getItemDetails(String itemId) {
        return restTemplate.getForObject(ITEM_SERVICE_PATH+"/"+itemId, Object.class);
    }

    public Object getUserDetailsByToken(String tokenId) {
        return restTemplate.getForObject(USER_BY_TOKEN+"/" + tokenId, Object.class);
    }


}

package com.demien.sprcloud.cartservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "userClient", name = "edge-server")
@RibbonClient(name = "user-service")
public interface UserServiceProxy {

	@RequestMapping(value = "/user-service/user/login", method = RequestMethod.POST)
	public String login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword);

	@RequestMapping(value = "/user-service/user/byToken/{tokenId}", method = RequestMethod.GET)
	public String userByToken(@PathVariable("tokenId") String tokenId);

}

package com.demien.sprmvc.controller;

import java.net.URI;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demien.sprmvc.domain.Greeting;
import com.demien.sprmvc.domain.User;

@Controller
public class UserController {

	private static final String helloWorldTemplate = "Hello World, %s!";
	private int id = 1;

	@RequestMapping(value = "/hello")
	public @ResponseBody String hello() {
		return "Hello world!";
	}

	@GetMapping("/hello-with-object")
	public @ResponseBody Greeting helloWithObject() {
		return new Greeting("Hello World", new Date());
	}

	@GetMapping("/hello-with-parameter/name/{name}")
	public @ResponseBody Greeting helloWithParameter(@PathVariable String name) {
		return new Greeting(String.format(helloWorldTemplate, name), new Date());
	}

	@PostMapping("/hello-post")
	public ResponseEntity<?> postTest(@RequestBody User user) {
		Greeting result = new Greeting(String.format(helloWorldTemplate, user.getName()), new Date());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id++).toUri();
		return ResponseEntity.created(location).body(result);
	}

}

package com.demien.sprmvc.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demien.sprmvc.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void helloTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello world!")));
	}

	@Test
	public void helloWithObjectTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello-with-object").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Hello World")));
	}

	@Test
	public void helloWithParameterTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello-with-parameter/name/Buddy").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Hello World, Buddy")));
	}

	@Test
	public void postTest() throws Exception {
		User user = new User("Joe");
		String userJson = mapper.writeValueAsString(user);
		mvc.perform(
				MockMvcRequestBuilders.post("/hello-post").content(userJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().string(containsString("Hello World, Joe")));
	}

}
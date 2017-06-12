package com.demien.ssecurity.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demien.ssecurity.testapp.model.Topic;

@Controller
@RequestMapping("/topic")
public class TopicController extends AbstractController<Topic, Long> {

	public TopicController() {
		super(Topic.class);
	}

}

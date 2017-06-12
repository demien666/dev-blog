package com.demien.ssecurity.testapp.controller;

import com.demien.ssecurity.testapp.model.User;
import com.demien.ssecurity.testapp.utils.JsonHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dmitry on 28.09.14.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public String sayHello() throws JsonHelper.JsonHelperException {
        return "hello world!";
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public String getById(@RequestParam Long id) throws JsonHelper.JsonHelperException {
        User user = new User();
        user.get(id);
        return JsonHelper.object2json(user);
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getAll() throws JsonHelper.JsonHelperException {
        User user = new User();
        List<User> users = user.getAll();
        return JsonHelper.object2json(users);
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(String json) throws JsonHelper.JsonHelperException {
        User user = (User) JsonHelper.Json2Object(json, User.class);
        user.save();
        return JsonHelper.object2json(user);
    }

}

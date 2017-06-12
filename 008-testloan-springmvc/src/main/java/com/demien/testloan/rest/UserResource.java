package com.demien.testloan.rest;

import static com.demien.testloan.AppConst.ERROR_CAN_NOT_DELETE;
import static com.demien.testloan.AppConst.RESULT_OK;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demien.testloan.domain.User;
import com.demien.testloan.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserResource extends BaseResource<User> {
    // services
    public static final String SERVICE_MAIN = "user";
    public static final String SERVICE_CREATE_USER = "createUser";
    public static final String SERVICE_UPDATE_USER = "updateUser";
    public static final String SERVICE_DELETE_USER = "deleteUser";

    public UserResource(final IUserService userService) {
        super(User.class, userService);
    }


    @RequestMapping(method=RequestMethod.GET, value="/createUser")
    public @ResponseBody String createUser(@PathVariable("login") final String login,
            @PathVariable("password") final String password)  {
        User user=new User(login, password);
        addEntity(user);
        return RESULT_OK;
    }

    @RequestMapping(method=RequestMethod.GET, value="/updateUser")
    public @ResponseBody String updateUser(@PathVariable("id") final Integer id, @PathVariable("login") final String login,
    		@PathVariable("password") final String password) {
        User user=new User(id, login, password);
        updateEntity(user);
        return RESULT_OK;
    }

    @RequestMapping(method=RequestMethod.GET, value="/deleteUser")
    public @ResponseBody String deleteUser(@PathVariable("id") final Integer id) {
        User user=new User();
        user.setId(id);
        String error=null;
        try {
            deleteEntity(user);
        } catch (Exception e) {
            error=ERROR_CAN_NOT_DELETE+e.getMessage() ;
        }
        if (error==null) {
            return RESULT_OK;
        } else {
            return error;
        }
    }



}

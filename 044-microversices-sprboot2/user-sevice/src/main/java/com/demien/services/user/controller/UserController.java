package com.demien.services.user.controller;

import com.demien.services.user.domain.User;
import com.demien.services.user.repository.TokenRepository;
import com.demien.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private void throwException(String message) {
        throw new RuntimeException(message);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String userId, @RequestParam String userPassword) {

        final User user = userRepository.findById(userId).orElseGet(() -> new User() );
        if (user == null) {
            throwException("User was not found in DB");
        }
        if (!user.getUserPassword().equals(userPassword)) {
            throwException("Wrong password");
        }

        return tokenRepository.getTokenForUser(userId).getTokenId();
    }

    @RequestMapping(value = "/byToken/{tokenId}", method = RequestMethod.GET)
    public User getUserByToken(@PathVariable String tokenId) {
        String userId = tokenRepository.getUserIdByToken(tokenId);
        User result = userRepository.getOne(userId);
        System.out.println("Result:" + result);
        return result;
    }


}

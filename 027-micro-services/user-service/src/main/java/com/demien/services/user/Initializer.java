package com.demien.services.user;

import com.demien.services.user.domain.User;
import com.demien.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        userRepository.save(new User("user1", "First User", "password1", "First Address"));
        userRepository.save(new User("user2", "Second User", "password2", "Second Address"));
    }
}

package com.demien.services.user.repository;

import com.demien.services.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface UserRepository extends JpaRepository<User, String> {
}

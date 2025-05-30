package com.enaa.helloevents.Repositories;

import com.enaa.helloevents.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositorie extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
}

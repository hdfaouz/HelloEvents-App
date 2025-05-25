package com.enaa.helloevents.Repositories;

import com.enaa.helloevents.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorie extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}

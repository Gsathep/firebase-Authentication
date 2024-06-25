package com.firbase.config.controller;

import com.firbase.config.domain.JwtUser;
import com.firbase.config.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<JwtUser, Long> {
    JwtUser findByContact(String contact);
    boolean existsByContact(String contact);

    Optional<User> findOneByLogin(String username);
}

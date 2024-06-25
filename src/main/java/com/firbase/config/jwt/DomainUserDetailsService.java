package com.firbase.config.jwt;



import com.firbase.config.controller.UserRepository;
import com.firbase.config.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findOneByLogin(username);
        if (!user.isPresent()) {
            throw new BadRequestAlertException("User not found", "user", "userNotFound");
        }
        ;		return new org.springframework.security.core.userdetails.User(user.get()., user.get()(), new ArrayList<>());
    }

}

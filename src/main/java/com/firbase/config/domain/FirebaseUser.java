package com.firbase.config.domain;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class FirebaseUser implements UserDetails {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public FirebaseUser(FirebaseToken token) {
        this.username = token.getUid();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    // Other UserDetails methods like isEnabled(), isAccountNonExpired(), etc.

    // Implement these methods according to your needs
}

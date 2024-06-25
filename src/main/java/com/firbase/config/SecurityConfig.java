package com.firbase.config;

import com.firbase.config.firbase.FirebaseAuthenticationProvider;
import com.firbase.config.jwt.DomainUserDetailsService;
import com.firbase.config.jwt.JwtAuthenticationEntryPoint;
import com.firbase.config.jwt.JwtAuthenticationFilter;
import com.firbase.config.jwt.JwtUtils;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    DomainUserDetailsService userDetailsService;
    JwtUtils jwtUtils;
    @Bean
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeRequests()
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/api/authenticate").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling(ex -> ex.authenticationEntryPoint((jwtAuthenticationEntryPoint))).authenticationProvider(daoAuthenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter()
    {
        return new JwtAuthenticationFilter(userDetailsService, jwtUtils);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public FirebaseAuthenticationProvider firebaseAuthenticationProvider() {
        return new FirebaseAuthenticationProvider();
    }
}

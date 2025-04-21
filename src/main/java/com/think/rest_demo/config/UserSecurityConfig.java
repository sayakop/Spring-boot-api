package com.think.rest_demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails userDetailsFirst = User.withUsername("User1")
        .password(passwordEncoder().encode("Pass1")).build();
        
        UserDetails userDetailsSecond = User.withUsername("User2")
        .password(passwordEncoder().encode("Pass2")).build();

        UserDetails admin = User.withUsername("Admin")
        .password(passwordEncoder().encode("Admin1")).build();

        return new InMemoryUserDetailsManager(userDetailsFirst,userDetailsSecond,admin);

    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}

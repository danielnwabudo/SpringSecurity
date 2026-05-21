package com.example.studentApi.services;

import com.example.studentApi.entity.Users;
import com.example.studentApi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
    //Its ONLY responsibility: load user information from database

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of());
    }
}

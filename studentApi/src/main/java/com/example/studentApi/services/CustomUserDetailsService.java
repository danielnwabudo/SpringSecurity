package com.example.studentApi.services;

import com.example.studentApi.entity.Users;
import com.example.studentApi.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        // TODO: An empty authority list (List.of()) means the user has NO granted roles.
        // This silently breaks any role-based access control you might add later.
        // Map the user's Role enum to a GrantedAuthority so Spring Security can enforce it.
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}

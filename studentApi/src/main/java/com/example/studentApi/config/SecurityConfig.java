package com.example.studentApi.config;

import com.example.studentApi.services.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Anything starting with: "/auth/" is public.
                /*Examples: auth/login, auth/register */
                //No JWT needed.
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**")
                .permitAll()
                        //.anyRequest().authenticated() means every other endpoint requires authentication.
                        .anyRequest().authenticated() )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return  http.build();
    }
}
/*
This config class DOES NOT authenticate users itself.

It only:

Define rules
plugs in filters
configures Spring Security behavior
 */

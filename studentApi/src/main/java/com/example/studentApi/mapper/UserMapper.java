package com.example.studentApi.mapper;

import com.example.studentApi.dto.RegisterRequestDto;
import com.example.studentApi.dto.RegisterResponseDto;
import com.example.studentApi.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public Users toEntity(RegisterRequestDto dto){
        Users user = new Users();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return  user;
    }
    public RegisterResponseDto toResponse(Users user){
        return new RegisterResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}

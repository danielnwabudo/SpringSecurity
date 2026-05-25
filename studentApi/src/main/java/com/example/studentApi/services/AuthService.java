package com.example.studentApi.services;

import com.example.studentApi.dto.LoginRequestDto;
import com.example.studentApi.dto.LoginResponseDto;
import com.example.studentApi.dto.RegisterRequestDto;
import com.example.studentApi.dto.RegisterResponseDto;
import com.example.studentApi.entity.Users;
import com.example.studentApi.enums.Role;
import com.example.studentApi.exception.EmailAlreadyExistsException;
import com.example.studentApi.exception.InvalidCredentialsException;
import com.example.studentApi.mapper.UserMapper;
import com.example.studentApi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, UserMapper mapper, PasswordEncoder encoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public RegisterResponseDto register(RegisterRequestDto dto){
        if(userRepository.existsByEmail(dto.email())){
            throw new EmailAlreadyExistsException( "Email already exist");
        }
        Users user = mapper.toEntity(dto);
        user.setPassword(encoder.encode(dto.password()));
        user.setRole(Role.USER);
        Users savedUser = userRepository.save(user);
        return mapper.toResponse(savedUser);
    }
    public LoginResponseDto login(LoginRequestDto dto) {
        // TODO: Raw RuntimeException was used — replace with custom exceptions so GlobalExceptionHandler
        // can return 401 Unauthorized instead of 500 Internal Server Error.
        Users user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponseDto(token);
    }
}
/*Full JWT Flow
Request comes in

↓

Filter reads Authorization header

↓

Extract JWT

↓

Validate JWT

↓

Extract email

↓

Load user from DB

↓

Create Authentication object

↓

Store inside SecurityContext

↓

Spring now considers user authenticated

↓

Controller executes
*/


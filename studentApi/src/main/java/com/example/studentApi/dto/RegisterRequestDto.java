package com.example.studentApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(@NotBlank(message = "Username should not be empty")
                                 String username,

                                 @NotBlank
                                 @Email(message = "Enter a valid email address")
                                 String email,

                                 @NotBlank
                                 String password) {
}

package com.example.studentApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDto(
                                @NotNull
                                String name,

                                @Email
                                String email,

                                @Min(1)
                                int age) {
}

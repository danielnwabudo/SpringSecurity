package com.example.studentApi.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDto(
        @NotBlank(message = "Title should not be empty")
        String title) {
}

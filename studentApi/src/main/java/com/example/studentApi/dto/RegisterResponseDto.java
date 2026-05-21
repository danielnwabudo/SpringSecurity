package com.example.studentApi.dto;

import com.example.studentApi.enums.Role;

public record RegisterResponseDto(Long id,

                                  String username,

                                  String email,

                                  Role role) {
}

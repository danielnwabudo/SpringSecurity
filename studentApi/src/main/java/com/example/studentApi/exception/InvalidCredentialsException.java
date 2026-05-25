package com.example.studentApi.exception;

/**
 * Thrown when login credentials (email or password) are invalid.
 *
 * TODO: Always use a custom exception (not raw RuntimeException) so the GlobalExceptionHandler
 * can return a consistent HTTP response (401 Unauthorized) instead of leaking an unhandled 500.
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}


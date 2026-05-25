package com.example.studentApi;

/**
 * TODO: This class exists only as a comment block — it is not needed as a Java file.
 * Move this documentation into the project's README.md where it will be more visible and maintainable.
 *
 * Security request flow:
 * 1. User logs in → JWT generated
 * 2. User sends JWT in Authorization header
 * 3. JwtAuthenticationFilter intercepts the request
 * 4. Extracts email from token → validates token signature and expiry
 * 5. Loads UserDetails from DB via CustomUserDetailsService
 * 6. Sets Authentication in SecurityContextHolder
 * 7. Spring Security sees an authenticated user
 * 8. Controller executes
 */
public class Security_Flow {
    // TODO: Also rename this class to SecurityFlow (PascalCase, no underscores) per Java naming conventions.
}

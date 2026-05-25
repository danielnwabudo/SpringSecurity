# Review Feedback Summary

This branch (`code-review/improvements`) contains a senior-style review pass over the junior engineer's Spring Boot project. The goal was not only to point out issues, but to leave behind concrete, learning-oriented fixes and notes.

## What was changed

### 1) Security hardening
- Upgraded JJWT from `0.11.5` to `0.12.7` in `studentApi/pom.xml`.
- Replaced the hard-coded JWT secret with a configuration-driven value in `studentApi/src/main/resources/application.properties`.
- Added `jwt.secret` support and token validation to `JwtService`.
- Updated `JwtAuthenticationFilter` to validate tokens before trusting their contents.
- Replaced `System.out.println` with SLF4J logging.
- Fixed `CustomUserDetailsService` to grant authorities based on the user's `Role` instead of returning an empty list.

### 2) Authentication and error handling improvements
- Replaced raw `RuntimeException` usage in `AuthService` with a dedicated `InvalidCredentialsException`.
- Added missing exception handling in `GlobalExceptionHandler` for:
  - `EmailAlreadyExistsException` → HTTP `409 Conflict`
  - `InvalidCredentialsException` → HTTP `401 Unauthorized`
  - `MethodArgumentNotValidException` → structured HTTP `400 Bad Request` validation responses

### 3) Controller cleanup and REST consistency
- Fixed inconsistent and confusing endpoint mappings in `StudentController`.
- Added `@Valid` to the student update endpoint so validation runs on updates too.
- Renamed controller methods to match what they actually do.
- Improved `CourseController` naming and path variable style (`studentId` instead of `student_id`).

### 4) Entity and persistence improvements
- Added `@Table(name = "app_users")` to `Users` to avoid reserved-word issues.
- Added `@Column(nullable = false)` and `@Column(unique = true)` constraints where appropriate.
- Added `@Column(nullable = false)` to `Course.title`.

### 5) Configuration and repository hygiene
- Externalized database and JWT settings using environment-variable placeholders.
- Added a `.gitignore` file to keep local/generated files and sensitive config out of version control.
- Added `application.properties.example` as a template for local setup.

### 6) Code cleanup and maintainability
- Removed unused imports and the unused `ctx` variable from `StudentApiApplication`.
- Reworked `Security_Flow.java` to highlight that it should be documentation, not a real source file.
- Added notes encouraging moving that explanation into `README.md`.

## Why these changes matter
- They reduce security risk.
- They make API behavior more predictable for clients.
- They improve validation and error messages.
- They set better standards for naming, configuration, and code organization.
- They show the junior engineer what “production-ready” code looks like.

## Notes for follow-up
- `application.properties` still exists in this branch history, but should ideally be replaced by a committed template and environment-specific local config.
- The comments marked `TODO` are intentional: they explain review feedback directly in the code so the engineer can learn from each change.
- `Security_Flow.java` should eventually be removed or moved into project documentation.

## Suggested next steps
1. Create or update `README.md` with setup instructions and the security flow.
2. Add tests around auth failures and validation errors.
3. Consider adding authorization rules for `ADMIN` vs `USER` roles.
4. Remove any committed local IDE files from the repository if they are not meant to be versioned.


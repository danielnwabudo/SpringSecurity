package com.example.studentApi;

public class Security_Flow {
    /*
    HUGE PICTURE OF YOUR SECURITY FLOW
1. User logs in

↓

2. JWT generated

↓

3. User sends JWT in Authorization header

↓

4. JwtAuthenticationFilter intercepts request

↓

5. Extracts email from token

↓

6. Loads user from DB

↓

7. Sets SecurityContext

↓

8. Spring Security sees authenticated user

↓

9. Controller executes
     */
}

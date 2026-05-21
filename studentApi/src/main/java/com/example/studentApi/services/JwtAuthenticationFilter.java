package com.example.studentApi.services;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
//JwtAuthenticationFilter extends OncePerRequestFilter because filter runs once per request.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService detailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService detailsService) {
        this.jwtService = jwtService;
        this.detailsService = detailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Read authorization header
        final String authHeader = request.getHeader("Authorization");

        // Check bearer chain(the condition: if no Twt continue request normally).
        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(request, response);  //This means: continue request processing
            return;
        }
        String jwt = authHeader.substring(7);
        String email = jwtService.extractEmail(jwt);
        System.out.println(email);
        // Step 1 check authentication context
        //Spring Security uses:SecurityContextHolder to know:"who is currently logged in?"
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

            // Step 2- Load UserDetails
            UserDetails userDetails = detailsService.loadUserByUsername(email);

            // Step 3- create authentication token
            // This tell spring security that this request belongs to authenticated user
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }
        //This means: continue request processing
        filterChain.doFilter(request,response);

    }
}
//This method intercepts every HTTP request. An HTTP request looks like this normally
    /*
    GET /students
    Authorization: Bearer eyJhbGc...
    So request.getHeader("Authorization"); is used to retrieve the Authorization header.
    The "Authorization" header starts with "Bearer " followed by the Jwt token.
    So  String jwt = authHeader.substring(7); jwt saves the token and exclude "Bearer " which has 6 characters + the space
     */
/*
No JWT means:

no email extraction
no UserDetails loading
no Authentication object
no SecurityContext authentication

So Spring Security still sees:

anonymous user
 */

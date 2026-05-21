package com.example.studentApi.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey123456";

    private Key getSignInKey(){
        //byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY); we are not using this because the SECRET_KEY is not Base64 encoded,
        // and it can throw a run time exception

        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    /*
    How JWT Generation Actually Works
    One Master Secret: The server holds a single, highly secure secret key. Users never see or know this key.
    User Information: When you log in with your personal password, the server verifies it once.
    It then creates a JWT containing basic, public user data (like your User ID).
    The Digital Signature: The server takes that user data, combines it with its own master secret key,
    and runs it through a cryptographic algorithm to create a unique signature.
    Token Delivery: This signed token is sent back to your browser.
    For all future requests, your browser sends this token back to the server to prove you are logged in.
     */

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    /*
    Spring Security is the standard, highly customizable framework used to secure Java applications.
    Its primary purpose is to handle Authentication (verifying who you are) and
    Authorization (controlling what you are allowed to access),
    while protecting your app against common cyber threats out of the box.
     */
}

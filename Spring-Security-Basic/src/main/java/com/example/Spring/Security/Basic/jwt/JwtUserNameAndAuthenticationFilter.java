package com.example.Spring.Security.Basic.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

/*
 This class handles authentication of User
 */
public class JwtUserNameAndAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // Read data from Request and map it to our own Class
    private AuthenticationManager authenticationManager;

    public JwtUserNameAndAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        };


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        try {
            // Read data from Request and map it to our own Class
            UserNameAndPasswordAuthenticationRequest authenticationRequest =
                    new ObjectMapper().readValue(request.getInputStream(), UserNameAndPasswordAuthenticationRequest.class);

            // A token
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            Authentication authenticate = this.authenticationManager.authenticate(authentication);

            return authenticate;

        }catch(IOException e){
            throw new RuntimeException(e);
        }
        //return super.attemptAuthentication(request, response);
    }

    // if authentication is successful

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                             Authentication authResult) throws IOException, ServletException {
        // Jwt builder
       String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(SignatureAlgorithm.HS512,"secretssssssssssssssssssssssssssssssssssssssssffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
                .compact();
        response.addHeader("Authorization","Bearer " + token);
        //super.successfulAuthentication(request, response, chain, authResult);
    }




}

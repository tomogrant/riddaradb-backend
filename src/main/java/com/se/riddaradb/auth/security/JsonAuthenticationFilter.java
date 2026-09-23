package com.se.riddaradb.auth.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

//Allows Spring Security to read JSON login requests
public class JsonAuthenticationFilter extends AuthenticationFilter {

    public JsonAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper){
        super(authenticationManager, (request -> {
            try {
                LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
                return new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            }
            catch (IOException e) {
                throw new AuthenticationServiceException("Could not read login request", e);
            }
        }));
    }
}

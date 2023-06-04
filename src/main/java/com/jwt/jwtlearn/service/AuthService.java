package com.jwt.jwtlearn.service;

import com.jwt.jwtlearn.entity.AuthRequest;
import com.jwt.jwtlearn.entity.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

   public AuthResponse generateToken(AuthRequest authRequest){
        //it is done because if the user is not in the database then we cannot give token to anyone
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));

        if(authenticate.isAuthenticated()){
           String jwtToken =   jwtService.generateToken(authRequest.getName());

           return AuthResponse.builder()
                   .token(jwtToken)
                   .build();
        }
        throw new UsernameNotFoundException("Useer does not exist!!!");
    }
}

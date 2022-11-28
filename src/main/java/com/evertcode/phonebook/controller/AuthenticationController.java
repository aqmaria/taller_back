package com.evertcode.phonebook.controller;

import com.evertcode.phonebook.config.security.CustomUserDetailsService;
import com.evertcode.phonebook.util.JWTUtil;
import com.evertcode.phonebook.dto.request.AuthenticationRequest;
import com.evertcode.phonebook.dto.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
       try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
           UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
           String jwt = jwtUtil.generateToken(userDetails);
           return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
       }catch (BadCredentialsException e){
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }
    }
}

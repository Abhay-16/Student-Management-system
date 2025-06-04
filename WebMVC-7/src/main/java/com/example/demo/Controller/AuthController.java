package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.JwtRequest;
import com.example.demo.response.JwtResponse;
import com.example.demo.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
    	System.out.println(request);
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}

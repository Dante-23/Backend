package com.learnthistime.learnthistime.controllers;

import com.learnthistime.learnthistime.service.AuthenticationService;
import com.learnthistime.learnthistime.utils.AuthenticationRequest;
import com.learnthistime.learnthistime.utils.AuthenticationResponse;
import com.learnthistime.learnthistime.utils.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
//        service.refreshToken(request, response);
    }

    @GetMapping()
    public String sendMessage() {
        return "Authentication get request";
    }

    @GetMapping("/register")
    public String sendMessage1() {
        return "Authentication get request for /request";
    }

}

package com.Digi_Med.Hospital.Management.controller;


import com.Digi_Med.Hospital.Management.dtos.LoginRequest;
import com.Digi_Med.Hospital.Management.dtos.LoginResponse;
import com.Digi_Med.Hospital.Management.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}

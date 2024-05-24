package com.myapp.budget.controller;

import com.myapp.budget.dto.LoginDto;
import com.myapp.budget.dto.LoginResponseDto;
import com.myapp.budget.dto.RegisterDto;
import com.myapp.budget.dto.RegisterResponseDto;
import com.myapp.budget.exceptions.RoleNotFoundException;
import com.myapp.budget.exceptions.UserAlreadyExistsException;
import com.myapp.budget.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) throws RoleNotFoundException,
            UserAlreadyExistsException {
        return authService.signUpUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        return authService.signInUser(loginDto);
    }
}

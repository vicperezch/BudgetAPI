package com.myapp.budget.controller;

import com.myapp.budget.dto.LoginDto;
import com.myapp.budget.dto.LoginResponseDto;
import com.myapp.budget.dto.RegisterDto;
import com.myapp.budget.dto.RegisterResponseDto;
import com.myapp.budget.exceptions.RoleNotFoundException;
import com.myapp.budget.exceptions.UserAlreadyExistsException;
import com.myapp.budget.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() throws RoleNotFoundException, UserAlreadyExistsException {
        RegisterDto registerDto = new RegisterDto("testUser", "testMail", "testPassword");
        ResponseEntity<RegisterResponseDto> expectedResponse = ResponseEntity.ok(new RegisterResponseDto("Account has been successfully created"));

        when(authService.signUpUser(registerDto)).thenReturn(expectedResponse);

        ResponseEntity<RegisterResponseDto> actualResponse = authController.register(registerDto);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testLogin() {
        LoginDto loginDto = new LoginDto("testMail", "testPassword");
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .id("1")
                .role("user")
                .token("token")
                .email("testMail")
                .username("testUser")
                .build();
        ResponseEntity<LoginResponseDto> expectedResponse = ResponseEntity.ok(loginResponseDto);

        when(authService.signInUser(loginDto)).thenReturn(expectedResponse);

        ResponseEntity<LoginResponseDto> actualResponse = authController.login(loginDto);

        assertEquals(expectedResponse, actualResponse);
    }
}

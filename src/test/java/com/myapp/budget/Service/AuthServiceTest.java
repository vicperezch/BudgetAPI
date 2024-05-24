package com.myapp.budget.Service;

import com.myapp.budget.dto.RegisterDto;
import com.myapp.budget.exceptions.RoleNotFoundException;
import com.myapp.budget.exceptions.UserAlreadyExistsException;
import com.myapp.budget.model.User;
import com.myapp.budget.repository.UserRepository;
import com.myapp.budget.security.JwtUtil;
import com.myapp.budget.security.UserDetailsImpl;
import com.myapp.budget.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUpUser() throws UserAlreadyExistsException, RoleNotFoundException {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("test@test.com");
        registerDto.setUsername("test");
        registerDto.setPassword("password");

        when(userRepository.existsByEmail(registerDto.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(registerDto.getUsername())).thenReturn(false);

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());

        when(userRepository.save(any(User.class))).thenReturn(user);

        String token = "token";
        when(jwtUtil.generateToken(any(UserDetailsImpl.class), any())).thenReturn(token);

        assertEquals(token, authService.signUpUser(registerDto).getBody());
    }
}

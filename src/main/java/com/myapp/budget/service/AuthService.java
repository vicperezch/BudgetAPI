package com.myapp.budget.service;

import com.myapp.budget.dto.LoginDto;
import com.myapp.budget.dto.LoginResponseDto;
import com.myapp.budget.dto.RegisterDto;
import com.myapp.budget.dto.RegisterResponseDto;
import com.myapp.budget.exceptions.RoleNotFoundException;
import com.myapp.budget.exceptions.UserAlreadyExistsException;
import com.myapp.budget.model.User;
import com.myapp.budget.repository.UserRepository;
import com.myapp.budget.security.JwtUtil;
import com.myapp.budget.security.UserDetailsImpl;
import com.myapp.budget.util.RoleFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ResponseEntity<RegisterResponseDto> signUpUser(RegisterDto registerDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserAlreadyExistsException("Provided email already exists.");
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserAlreadyExistsException("Provided username already exists.");
        }

        User user = createUser(registerDto);
        userRepository.save(user);

        RegisterResponseDto registerResponseDto = RegisterResponseDto.builder()
                .message("Account has been successfully created")
                .build();

        return new ResponseEntity<>(registerResponseDto, HttpStatus.CREATED);
    }

    public ResponseEntity<LoginResponseDto> signInUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> optionalUser = userRepository.findByEmail(loginDto.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String jwt = jwtUtil.generateToken(UserDetailsImpl.build(user), user.getRole());

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).toList();

            LoginResponseDto signInResponseDto = LoginResponseDto.builder()
                    .username(userDetails.getUsername())
                    .email(userDetails.getEmail())
                    .id(userDetails.getId())
                    .token(jwt)
                    .role(roles.getFirst())
                    .build();

            return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
        }

        throw new UsernameNotFoundException("Provided username not found.");
    }

    private User createUser(RegisterDto registerDto) throws RoleNotFoundException {
        return User.builder()
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(RoleFactory.getInstance(registerDto.getRole()))
                .build();
    }
}

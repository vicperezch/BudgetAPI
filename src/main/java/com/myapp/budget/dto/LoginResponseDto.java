package com.myapp.budget.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private String id;
    private String username;
    private String email;
    private String role;
}

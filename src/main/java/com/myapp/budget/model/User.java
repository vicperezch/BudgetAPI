package com.myapp.budget.model;

import com.myapp.budget.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @MongoId
    private String id;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String username;
    private String password;
}

package com.myapp.budget.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @MongoId
    private String id;
    private String email;
    private String username;
    private String password;
}

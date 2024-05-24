package com.myapp.budget.util;

import com.myapp.budget.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    public static Role getInstance(String role) throws RoleNotFoundException {
        try {
            return switch (role.toLowerCase()) {
                case "admin" -> Role.ADMIN;
                case "user" -> Role.USER;
                default -> throw new RoleNotFoundException("No role found for " + role);
            };

        } catch (NullPointerException e) {
            return Role.USER;
        }
    }
}

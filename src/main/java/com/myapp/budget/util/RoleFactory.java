package com.myapp.budget.util;

import com.myapp.budget.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    public static Role getInstance(String role) throws RoleNotFoundException {
        switch (role.toLowerCase()) {
            case "admin" -> {
                return Role.ADMIN;
            }
            case "user" -> {
                return Role.USER;
            }

            default -> throw new RoleNotFoundException("No role found for " +  role);
        }
    }
}

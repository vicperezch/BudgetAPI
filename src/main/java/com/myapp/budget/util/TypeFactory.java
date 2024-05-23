package com.myapp.budget.util;

import com.myapp.budget.exceptions.TypeNotFoundException;

public class TypeFactory {
    public static Type getInstance(String type) throws TypeNotFoundException {
        switch (type.toLowerCase()) {
            case "income" -> {
                return Type.INCOME;
            }
            case "expense" -> {
                return Type.EXPENSE;
            }

            default -> throw new TypeNotFoundException("No role found for " +  type);
        }
    }
}

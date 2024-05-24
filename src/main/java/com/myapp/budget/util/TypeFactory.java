package com.myapp.budget.util;

import com.myapp.budget.exceptions.TypeNotFoundException;

public class TypeFactory {
    public static Type getInstance(String type) throws TypeNotFoundException {
        return switch (type.toLowerCase()) {
            case "income" -> Type.INCOME;
            case "expense" -> Type.EXPENSE;
            default -> throw new TypeNotFoundException("No type found for " +  type);
        };
    }
}

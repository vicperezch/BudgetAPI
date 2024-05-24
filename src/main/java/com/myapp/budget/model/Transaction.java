package com.myapp.budget.model;

import com.myapp.budget.util.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Type type;
    private double amount;
    private String description;
    private String userId;
}

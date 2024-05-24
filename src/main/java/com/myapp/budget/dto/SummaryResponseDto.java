package com.myapp.budget.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryResponseDto {
    private String category;
    private long NumberOfTransactions;
    private double totalAmount;
}

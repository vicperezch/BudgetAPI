package com.myapp.budget.service;

import com.myapp.budget.dto.SummaryResponseDto;
import com.myapp.budget.exceptions.TypeNotFoundException;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.repository.TransactionRepository;
import com.myapp.budget.security.UserDetailsImpl;
import com.myapp.budget.util.TypeFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SummaryService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllUserTransactions() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return transactionRepository.findAllByUserId(user.getId());
    }

    public List<Transaction> getUserTransactionsByCategory(String category) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return transactionRepository.findAllByUserIdAndCategory(user.getId(), category);
    }

    public List<Transaction> getUserTransactionsByType(String type) throws TypeNotFoundException {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return transactionRepository.findAllByUserIdAndType(user.getId(), TypeFactory.getInstance(type));
    }

    public List<Transaction> getUserTransactionsByMonth(String month) {
        List<Transaction> userTransactions = getAllUserTransactions();
        List<Transaction> monthTransactions = new ArrayList<>();

        for (Transaction transaction : userTransactions) {
            if (transaction.getDate().toString().substring(5, 7).toLowerCase().equals(month)) {
                monthTransactions.add(transaction);
            }
        }

        return monthTransactions;
    }

    public List<SummaryResponseDto> getUserSummary() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return transactionRepository.findCategorySummaries(user.getId());
    }
}

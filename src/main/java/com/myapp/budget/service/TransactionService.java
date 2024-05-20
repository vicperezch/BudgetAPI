package com.myapp.budget.service;

import com.myapp.budget.model.Transaction;
import com.myapp.budget.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findById(int id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        if (transaction.isPresent()) {
            return transaction.get();
        }

        return null;
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransactionById(int id) {
        transactionRepository.deleteById(id);
    }
}

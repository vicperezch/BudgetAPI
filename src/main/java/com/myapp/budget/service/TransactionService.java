package com.myapp.budget.service;

import com.myapp.budget.dto.TransactionDto;
import com.myapp.budget.exceptions.TypeNotFoundException;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.repository.TransactionRepository;
import com.myapp.budget.security.UserDetailsImpl;
import com.myapp.budget.util.TypeFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(int id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        if (transaction.isPresent()) {
            return transaction.get();
        }

        return null;
    }

    public Transaction save(TransactionDto transactionDto) throws TypeNotFoundException {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Transaction transaction = Transaction.builder()
                .date(transactionDto.getDate())
                .amount(transactionDto.getAmount())
                .description(transactionDto.getDescription())
                .userId(user.getId())
                .type(TypeFactory.getInstance(transactionDto.getType()))
                .build();

        return transactionRepository.save(transaction);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteById(int id) {
        transactionRepository.deleteById(id);
    }
}

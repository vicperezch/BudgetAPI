package com.myapp.budget.controller;

import com.myapp.budget.dto.TransactionDto;
import com.myapp.budget.exceptions.TypeNotFoundException;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
        return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws TypeNotFoundException {
        return new ResponseEntity<>(transactionService.save(transactionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody TransactionDto newTransaction) {
        Transaction transaction = transactionService.findById(id);

        if (transaction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        transaction.setCategory(newTransaction.getCategory());
        transaction.setAmount(newTransaction.getAmount());
        transaction.setDescription(newTransaction.getDescription());
        transaction.setDate(newTransaction.getDate());

        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable int id) throws IllegalAccessException {
        transactionService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

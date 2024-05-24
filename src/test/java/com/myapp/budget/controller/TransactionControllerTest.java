package com.myapp.budget.controller;

import com.myapp.budget.dto.TransactionDto;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {
    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionService.getAll()).thenReturn(transactions);
        ResponseEntity<List<Transaction>> response = transactionController.getAllTransactions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testGetTransactionById() {
        Transaction transaction = new Transaction();
        when(transactionService.findById(1)).thenReturn(transaction);
        ResponseEntity<Transaction> response = transactionController.getTransactionById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    public void testCreateTransaction() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        Transaction transaction = new Transaction();
        when(transactionService.save(transactionDto)).thenReturn(transaction);
        ResponseEntity<Transaction> response = transactionController.createTransaction(transactionDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    public void testUpdateTransaction() {
        TransactionDto transactionDto = new TransactionDto();
        Transaction transaction = new Transaction();
        when(transactionService.findById(1)).thenReturn(transaction);
        when(transactionService.save(transaction)).thenReturn(transaction);
        ResponseEntity<Transaction> response = transactionController.updateTransaction(1, transactionDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    public void testDeleteTransaction() throws Exception {
        doNothing().when(transactionService).deleteById(1);
        ResponseEntity<Transaction> response = transactionController.deleteTransaction(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
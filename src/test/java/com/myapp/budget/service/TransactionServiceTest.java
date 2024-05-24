package com.myapp.budget.service;

import com.myapp.budget.model.Transaction;
import com.myapp.budget.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TransactionServiceTest {
    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        transactionService.getAll();
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        transactionService.findById(1);
        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveWithTransaction() {
        Transaction transaction = new Transaction();
        transactionService.save(transaction);
        verify(transactionRepository, times(1)).save(transaction);
    }
}

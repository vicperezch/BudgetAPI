package com.myapp.budget.controller;

import com.myapp.budget.controller.SummaryController;
import com.myapp.budget.service.SummaryService;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.dto.SummaryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SummaryControllerTest {
    private SummaryController summaryController;
    private SummaryService summaryService;

    @BeforeEach
    public void setup() {
        summaryService = Mockito.mock(SummaryService.class);
        summaryController = new SummaryController(summaryService);
    }

    @Test
    public void testGetTransactionsByUser() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(summaryService.getAllUserTransactions()).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = summaryController.getTransactionsByUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testGetTransactionsByCategory() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(summaryService.getUserTransactionsByCategory("category")).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = summaryController.getTransactionsByCategory("category");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testGetTransactionsByType() throws Exception {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(summaryService.getUserTransactionsByType("type")).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = summaryController.getTransactionsByType("type");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testGetTransactionsByDate() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(summaryService.getUserTransactionsByMonth("month")).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = summaryController.getTransactionsByDate("month");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testGetSummary() {
        List<SummaryResponseDto> summary = Collections.singletonList(new SummaryResponseDto());
        when(summaryService.getUserSummary()).thenReturn(summary);

        ResponseEntity<List<SummaryResponseDto>> response = summaryController.getSummary();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(summary, response.getBody());
    }
}

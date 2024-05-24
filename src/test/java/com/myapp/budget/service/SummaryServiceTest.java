package com.myapp.budget.service;

import com.myapp.budget.exceptions.TypeNotFoundException;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.repository.TransactionRepository;
import com.myapp.budget.security.UserDetailsImpl;
import com.myapp.budget.util.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SummaryServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private SummaryService summaryService;
    private SimpleDateFormat dateFormat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Test
    public void testGetAllUserTransactions() {
        UserDetailsImpl testUser = new UserDetailsImpl("1", "USER","testMail", "testUser", "testPassword");
        Transaction testTransaction1 = new Transaction();
        Transaction testTransaction2 = new Transaction();
        List<Transaction> testTransactions = Arrays.asList(testTransaction1, testTransaction2);

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(testUser);
        when(transactionRepository.findAllByUserId(any(String.class))).thenReturn(testTransactions);

        List<Transaction> result = summaryService.getAllUserTransactions();

        assertEquals(testTransactions, result);
    }

    @Test
    public void testGetUserTransactionsByCategory() {
        UserDetailsImpl testUser = new UserDetailsImpl("1", "USER","testMail", "testUser", "testPassword");
        Transaction testTransaction1 = new Transaction();
        Transaction testTransaction2 = new Transaction();
        List<Transaction> testTransactions = Arrays.asList(testTransaction1, testTransaction2);

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(testUser);
        when(transactionRepository.findAllByUserIdAndCategory(any(String.class), any(String.class))).thenReturn(testTransactions);

        List<Transaction> result = summaryService.getUserTransactionsByCategory("testCategory");

        assertEquals(testTransactions, result);
    }

    @Test
    public void testGetUserTransactionsByType() throws TypeNotFoundException, ParseException {
        UserDetailsImpl testUser = new UserDetailsImpl("1", "USER","testMail", "testUser", "testPassword");
        Transaction testTransaction1 = new Transaction(1, "testCategory", dateFormat.parse("23/05/2024"), Type.INCOME, 12.02, null, "1");
        Transaction testTransaction2 = new Transaction(1, "testCategory", dateFormat.parse("23/05/2024"), Type.INCOME, 12.02, null, "1");
        List<Transaction> testTransactions = Arrays.asList(testTransaction1, testTransaction2);

        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(testUser);
        when(transactionRepository.findAllByUserIdAndType(any(String.class), any(Type.class))).thenReturn(testTransactions);

        List<Transaction> result = summaryService.getUserTransactionsByType("income");

        assertEquals(testTransactions, result);
    }
}
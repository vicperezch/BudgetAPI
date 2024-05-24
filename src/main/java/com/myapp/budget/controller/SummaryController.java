package com.myapp.budget.controller;

import com.myapp.budget.dto.SummaryResponseDto;
import com.myapp.budget.exceptions.TypeNotFoundException;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.service.SummaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/summary")
@AllArgsConstructor
public class SummaryController {
    private final SummaryService summaryService;

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getTransactionsByUser() {
        return new ResponseEntity<>(summaryService.getAllUserTransactions(), HttpStatus.OK);
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Transaction>> getTransactionsByCategory(@PathVariable String cat) {
        return new ResponseEntity<>(summaryService.getUserTransactionsByCategory(cat), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Transaction>> getTransactionsByType(@PathVariable String type) throws TypeNotFoundException {
        return new ResponseEntity<>(summaryService.getUserTransactionsByType(type), HttpStatus.OK);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(@PathVariable String month) {
        return new ResponseEntity<>(summaryService.getUserTransactionsByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SummaryResponseDto>> getSummary() {
        return new ResponseEntity<>(summaryService.getUserSummary(), HttpStatus.OK);
    }
}

package com.myapp.budget.repository;

import com.myapp.budget.dto.SummaryResponseDto;
import com.myapp.budget.model.Transaction;
import com.myapp.budget.util.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(String userId);
    List<Transaction> findAllByUserIdAndCategory(String userId, String category);
    List<Transaction> findAllByUserIdAndType(String userId, Type type);

    @Query("SELECT new com.myapp.budget.dto.SummaryResponseDto(t.category, COUNT(t), SUM(t.amount)) " +
            "FROM Transaction t WHERE t.userId = :userId GROUP BY t.category")
    List<SummaryResponseDto> findCategorySummaries(String userId);
}

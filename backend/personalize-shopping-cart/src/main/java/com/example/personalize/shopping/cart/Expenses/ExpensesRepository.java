package com.example.personalize.shopping.cart.Expenses;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    
    @Query("SELECT SUM(e.amount) FROM Expenses e")
    BigDecimal sumTotalExpenses();

}

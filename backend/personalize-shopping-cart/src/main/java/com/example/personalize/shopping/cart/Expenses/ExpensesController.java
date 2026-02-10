package com.example.personalize.shopping.cart.Expenses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/api/v1/expenses")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "https://localhost:5500", allowCredentials = "true")
public class ExpensesController {
    @Autowired
    ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @PostMapping()
    public ResponseEntity<Expenses> addNewExpense(@RequestBody Expenses expenses) {
        Expenses newExpenses = expensesService.addExpense(expenses);
        return ResponseEntity.ok(newExpenses);
    }

    @GetMapping()
    public ResponseEntity<List<Expenses>> viewAllExpenses() {
        List<Expenses> allExpenses = expensesService.getAllExpenses();
        return ResponseEntity.ok(allExpenses);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expenses> viewExpenseById(@PathVariable Long expenseId) {
        Expenses newExpenses = expensesService.getExpenseById(expenseId);
        return ResponseEntity.ok(newExpenses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Long id){
        expensesService.deleteExpense(id);
        return ResponseEntity.ok("An expenses was deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expenses> updateAnExpense(@PathVariable Long id, @RequestBody Expenses expense) {
        Expenses updatedExpense = expensesService.updateAnExpense(id,expense);
        return ResponseEntity.ok(updatedExpense);
    }
    
}

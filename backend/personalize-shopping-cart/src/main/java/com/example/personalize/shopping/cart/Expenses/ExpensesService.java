package com.example.personalize.shopping.cart.Expenses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

    @Autowired
    ExpensesRepository expensesRepository;

    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public Expenses addExpense(Expenses expense) {
        return expensesRepository.save(expense);
    }

    public Expenses getExpenseById(Long expenseId){
        return expensesRepository.findById(expenseId).orElse(null);
    }

    public List<Expenses> getAllExpenses(){
        return expensesRepository.findAll();
    }

    public Expenses updateAnExpense(Long Id, Expenses expense) {
        Expenses existingExpense = expensesRepository.findById(Id).orElse(null);
        if(existingExpense != null){
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setCreationDate(expense.getCreationDate());
            existingExpense.setExpenseDate(expense.getExpenseDate());
            existingExpense.setDueDate(expense.getDueDate());
            existingExpense.setPaidDate(expense.getPaidDate());
            existingExpense.setUserId(expense.getUserId());
            existingExpense.setVehicleId(expense.getVehicleId());
            existingExpense.setInvoiceNumber(expense.getInvoiceNumber());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setPaymentStatus(expense.getPaymentStatus());
            existingExpense.setVendor(expense.getVendor());
            existingExpense.setPaymentMethod(expense.getPaymentStatus());
            existingExpense.setDescription(expense.getDescription());
            return expensesRepository.save(existingExpense);
        }
        return null;
    }

    public void deleteExpense(Long expenseId){
        expensesRepository.deleteById(expenseId);
    }
    
}

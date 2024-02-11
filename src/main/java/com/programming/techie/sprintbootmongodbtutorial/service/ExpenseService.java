package com.programming.techie.sprintbootmongodbtutorial.service;

import com.programming.techie.sprintbootmongodbtutorial.model.Expense;
import com.programming.techie.sprintbootmongodbtutorial.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {
    private final ExpenseRepository expenseRepository;


    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
        System.out.println("id of this expense is " + expense.getId());
    }
    public void updateExpense(Expense expense) {
       Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(expense);

    }

    public Expense getExpenseByName(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Expense by Name %s", name)));
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public void deleteExpense(String id) {
        System.out.println("delete id: " + id);
        expenseRepository.deleteById(id);
    }
}
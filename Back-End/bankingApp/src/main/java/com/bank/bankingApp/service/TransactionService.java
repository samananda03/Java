package com.bank.bankingApp.service;

import com.bank.bankingApp.model.Transaction;
import com.bank.bankingApp.model.User;
import com.bank.bankingApp.repository.TransactionRepository;
import com.bank.bankingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService; // Inject UserService

    // Add a transaction for a user
    public Transaction addTransaction(Transaction transaction, Long userId) {
        // Ensure the user exists before adding transaction
        User user = userService.getUserById(userId); // Assume this method exists
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Set the user for the transaction
        transaction.setUser(user);

        // Ensure transaction date is set before saving (if not already set in the constructor)
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDateTime.now()); // Set current date and time if not set
        }

        // Save the transaction to the database
        return transactionRepository.save(transaction);
    }

    // Get all transactions for a specific user by userId
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public double getUserBalance(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals("deposit")) {
                balance += transaction.getAmount();
            } else if (transaction.getType().equals("withdrawal")) {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }
    // Additional methods for transaction logic (deposit, withdrawal, etc.)
}

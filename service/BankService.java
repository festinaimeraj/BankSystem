package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Transaction;
import repository.AccountRepository;
import exception.InsufficientFundsException;
import exception.AccountNotFoundException;
import util.DBConnection;

public class BankService {
    private AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        String query = "INSERT INTO Account (user_name, balance) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();  // Fixed here
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, account.getUserName());
            stmt.setDouble(2, account.getBalance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(String accountId, double amount) throws InsufficientFundsException, AccountNotFoundException {
        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("There's not enough funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void deposit(String accountId, double amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public Account viewAccountDetails(String accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Account";
        try (Connection conn = DBConnection.getConnection();  // Fixed here
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getString("account_id"));  // Fixed here
                account.setUserName(rs.getString("user_name"));
                account.setBalance(rs.getDouble("balance"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void createTransaction(Transaction transaction) {
        String query = "INSERT INTO Transaction (amount, originating_account_id, resulting_account_id, transaction_reason) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();  // Fixed here
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, transaction.getAmount());
            stmt.setInt(2, transaction.getOriginatingAccountId());
            stmt.setInt(3, transaction.getResultingAccountId());
            stmt.setString(4, transaction.getTransactionReason());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transaction WHERE originating_account_id = ? OR resulting_account_id = ?";
        try (Connection conn = DBConnection.getConnection();  // Fixed here
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountId);
            stmt.setString(2, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setOriginatingAccountId(rs.getInt("originating_account_id"));
                    transaction.setResultingAccountId(rs.getInt("resulting_account_id"));
                    transaction.setTransactionReason(rs.getString("transaction_reason"));
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Account> viewAllAcounts() {
        return accountRepository.findAll();
    }
}

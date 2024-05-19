# BankSystem

BankSystem is a console application for managing a bank system with multiple user accounts. It supports transactions such as withdrawals, deposits, and transfers between accounts. The system also charges transaction fees which can be either a flat fee or a percentage fee.
It has these features:
- Create a bank with required values.
- Create an account.
- Perform flat fee and percent fee transactions.
- Withdraw and deposit money into an account.
- View list of transactions for any account.
- Check account balances.
- View list of all bank accounts.
- View total transaction fee amount collected by the bank.
- View total transfer amount handled by the bank.

## Database Setup

CREATE DATABASE BankSystem;

USE BankSystem;

CREATE TABLE Account (
account_id INT auto_increment PRIMARY KEY,
user_name VARCHAR(100) NOT NULL,
balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00
);

CREATE TABLE Transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    originating_account_id INT,
    resulting_account_id INT,
    transaction_reason VARCHAR(255),
    FOREIGN KEY (originating_account_id) REFERENCES Account(account_id),
    FOREIGN KEY (resulting_account_id) REFERENCES Account(account_id)
);

CREATE TABLE Bank (
    bank_id INT AUTO_INCREMENT PRIMARY KEY,
    bank_name VARCHAR(100) NOT NULL,
    total_transaction_fee DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    total_transfer_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    flat_fee_amount DECIMAL(10, 2) NOT NULL DEFAULT 10.00,
    percent_fee_value DECIMAL(5, 2) NOT NULL DEFAULT 5.00
);

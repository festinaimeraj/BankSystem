package service;

import model.Account;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;
import exception.InsufficientFundsException;
import exception.AccountNotFoundException;

public class TransactionService {
	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	private double transactionFlatFeeAmount;
	private double transactionPercentFeeValue;
	
	public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.transactionFlatFeeAmount = transactionFlatFeeAmount;
		this.transactionPercentFeeValue = transactionPercentFeeValue;
	}
	
	public void performTransaction(String fromAccountId, String toAccountId, double amount, String reason, boolean isFlatFee) throws InsufficientFundsException, AccountNotFoundException {
		Account fromAccount = accountRepository.findById(fromAccountId);
		Account toAccount = accountRepository.findById(toAccountId);
		
		if(fromAccount == null || toAccount == null) {
			throw new AccountNotFoundException("Account not found");
			
		}
		
		double fee = isFlatFee ? transactionFlatFeeAmount : amount + transactionPercentFeeValue / 100;
		double totalAmount = amount + fee;
		
		if(fromAccount.getBalance() < totalAmount) {
			throw new InsufficientFundsException("There's not enough funds");
		}
		
		fromAccount.setBalance(fromAccount.getBalance() - totalAmount);
		toAccount.setBalance(toAccount.getBalance() + amount);
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transaction = new Transaction(amount, fromAccountId,toAccountId, reason);
		transactionRepository.save(transaction);
	}
	
	
	public double viewTotalTransactionFeeAmount() {
		return transactionRepository.getTotalTransactionFeeAmount();
	}
	
	public double viewTotalTransferAmount() {
		return transactionRepository.getTotalTransferAmount();
	}
	

}

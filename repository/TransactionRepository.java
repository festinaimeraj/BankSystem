package repository;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>();
	private double totalTransactionFeeAmount = 0;
	private double totalTransferAmount = 0;
	
	public void save(Transaction transaction) {
		transactions.add(transaction);
		totalTransferAmount += transaction.getAmount();
	}
	
	public List<Transaction> findAll() {
		return transactions;
	}
	
	public double getTotalTransactionFeeAmount() {
		return totalTransactionFeeAmount;
	}
	
	public double getTotalTransferAmount() {
		return totalTransferAmount;
	}

}

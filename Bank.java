package model;

import java.util.List;

public class Bank {
	private int bankId;
	private String bankName;
	private List<Account> accounts;
	private double totalTransactionFeeAmount;
	private double totalTransferAmount;
	private double transactionFlatFeeAmount;
	private double transactionPercentFeeValue;
	
	
	public Bank(int bankId, String bankName, double totalTransactionFeeAmount, double totalTransferAmount, double flatFeeAmount, double percentFeeValue, List<Account> accounts, double transactionPercentFeeValue, double transactionFlatFeeAmount) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
        this.totalTransferAmount = totalTransferAmount;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
        this.accounts = accounts;
    }
	
	 public void addAccount(Account account) {
	        accounts.add(account);
	    }

	    public List<Account> getAccounts() {
	        return accounts;
	    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getTotalTransactionFee() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFee(double totalTransactionFee) {
        this.totalTransactionFeeAmount = totalTransactionFee;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public double getFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public void setFlatFeeAmount(double flatFeeAmount) {
        this.transactionFlatFeeAmount = flatFeeAmount;
    }

    public double getPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    public void setPercentFeeValue(double percentFeeValue) {
        this.transactionPercentFeeValue = percentFeeValue;
    }

    

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    
    @Override
    public String toString() {
        return "Bank{" +
                "bankName='" + bankName + '\'' +
                ", accounts=" + accounts +
                ", totalTransactionFeeAmount=" + totalTransactionFeeAmount +
                ", totalTransferAmount=" + totalTransferAmount +
                ", transactionFlatFeeAmount=" + transactionFlatFeeAmount +
                ", transactionPercentFeeValue=" + transactionPercentFeeValue +
                '}';
    }
}

package model;

public class Transaction {
	private int transactionId;
	private double amount;
	private int originatingAccountId;
	private int resultingAccountId;
	private String transactionReason;
	
	public Transaction() {
    }
	
	
	public Transaction(int transactionId, double amount, int originatingAccountId, int resultingAccountId, String transactionReason) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }

    public Transaction(double amount2, String fromAccountId, String toAccountId, String reason) {
		// TODO Auto-generated constructor stub
	}

	public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOriginatingAccountId() {
        return originatingAccountId;
    }

    public void setOriginatingAccountId(int originatingAccountId) {
        this.originatingAccountId = originatingAccountId;
    }

    public int getResultingAccountId() {
        return resultingAccountId;
    }

    public void setResultingAccountId(int resultingAccountId) {
        this.resultingAccountId = resultingAccountId;
    }

    public String getTransactionReason() {
        return transactionReason;
    }

    public void setTransactionReason(String transactionReason) {
        this.transactionReason = transactionReason;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", originatingAccountId='" + originatingAccountId + '\'' +
                ", resultingAccountId='" + resultingAccountId + '\'' +
                ", transactionReason='" + transactionReason + '\'' +
                '}';
    }
}

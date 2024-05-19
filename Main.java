//import java.util.List;
//
//import model.Account;
//import model.Transaction;
//import service.BankService;
//
//public class Main {
//	public static void main(String[] args) {
//        BankService bankService = new BankService(null);
//
//        Account account1 = new Account();
//        Account account2 = new Account();
//        bankService.createAccount(account1);
//        bankService.createAccount(account2);
//
//        List<Account> accounts = bankService.getAllAccounts();
//        for (Account account : accounts) {
//            System.out.println("Account ID: " + account.getAccountId() + ", Name: " + account.getUserName() + ", Balance: " + account.getBalance());
//        }
//
//        Transaction transaction = new Transaction();
//        bankService.createTransaction(transaction);
//
//        List<Transaction> transactions = bankService.getTransactionsByAccountId(account1.getAccountId());
//        for (Transaction trans : transactions) {
//            System.out.println("Transaction ID: " + trans.getTransactionId() + ", Amount: " + trans.getAmount() + ", From: " + trans.getOriginatingAccountId() + ", To: " + trans.getResultingAccountId() + ", Reason: " + trans.getTransactionReason());
//        }
//    }
//}

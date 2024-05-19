import java.util.Scanner;

import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankService;
import service.TransactionService;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import model.Account;

public class BankApplication {

	public static void main(String[] args) {
		AccountRepository accountRepository = new AccountRepository();
		TransactionRepository transactionRepository = new TransactionRepository();
		BankService bankService = new BankService(accountRepository);
		TransactionService transactionService = new TransactionService(accountRepository, transactionRepository, 10, 5);
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("1. Create Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("5. View Account Details");
			System.out.println("6. View All Accounts");
			System.out.println("7. View Total Transation Fee Amount");
			System.out.println("8. View Total Transfer Amount");
			System.out.println("9. Exit");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			try {
				switch (choice) {
				case 1:
					System.out.println("Enter username");
					String userName = scanner.nextLine();
					System.out.println("Enter account ID:");
					String accountId = scanner.nextLine();
					System.out.println("Enter initial balance:");
					double initialBalance = scanner.nextDouble();
					Account account = new Account(accountId, userName, initialBalance);
					bankService.createAccount(account);
					System.out.println("Account created successfully");
					break;
					
					
				case 2:
					System.out.println("Enter account ID:");
					accountId = scanner.nextLine();
					System.out.println("Enter deposit amount:");
					double depositAmount = scanner.nextDouble();
					bankService.deposit(accountId, depositAmount);
					System.out.println("Deposit successful.");
					break;
					
					
				case 3:
					System.out.println("Enter account ID:");
					accountId = scanner.nextLine();
					System.out.println("Enter withdrawal amount:");
					double withdrawalAmount = scanner.nextDouble();
					bankService.withdraw(accountId, withdrawalAmount);
					System.out.println("Deposit successful.");
					break;
					
					
				case 4:
					System.out.println("Enter originating account ID:");
                    String fromAccountId = scanner.nextLine();
                    System.out.println("Enter resulting account ID:");
                    String toAccountId = scanner.nextLine();
                    System.out.println("Enter transfer amount:");
                    double transferAmount = scanner.nextDouble();
                    System.out.println("Is it a flat fee transaction? (true/false):");
                    boolean isFlatFee = scanner.nextBoolean();
                    scanner.nextLine();  
                    System.out.println("Enter transaction reason:");
                    String reason = scanner.nextLine();
                    transactionService.performTransaction(fromAccountId, toAccountId, transferAmount, reason, isFlatFee);
                    System.out.println("Transfer successful.");
                    break;

                case 5:
                    System.out.println("Enter account ID:");
                    String accountIdToView = scanner.nextLine();
                    Account accDetails = bankService.viewAccountDetails(accountIdToView);
                    System.out.println("Account Details: " + accDetails);
                    break;

                case 6:
                    System.out.println("List of all accounts:");
                    for (Account acc : bankService.viewAllAcounts()) {
                        System.out.println(acc);
                    }
                    break;

                case 7:
                    System.out.println("Total transaction fee amount: " + transactionService.viewTotalTransactionFeeAmount());
                    break;

                case 8:
                    System.out.println("Total transfer amount: " + transactionService.viewTotalTransferAmount());
                    break;

                case 9:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
				}
			} catch (AccountNotFoundException e) {
				System.err.println("Error: " + e.getMessage());
			} catch (InsufficientFundsException e) {
				System.err.println("Error: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("An unexpected error ocurred: " + e.getMessage());
			}
			
		}

	}

}

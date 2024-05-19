package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Account;

public class AccountRepository {
	private List<Account> accounts = new ArrayList<>();
	
	public void save(Account account) {
		Optional<Account> existingAccount = accounts.stream().filter(a -> a.getAccountId().equals(account.getAccountId())).findFirst();
		if (existingAccount.isPresent()) {
			accounts.remove(existingAccount.get());
		}
		accounts.add(account);
		
	}
	
	public Account findById(String accountId) {
		return accounts.stream().filter(account -> account.getAccountId().equals(accountId)).findFirst().orElse(null);
		
	}
	
	public List<Account> findAll() {
		return new ArrayList<>(accounts);
	}
}

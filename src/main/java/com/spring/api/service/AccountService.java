package com.spring.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.dto.AccountBalanceResponse;
import com.spring.api.dto.AccountRequest;
import com.spring.api.dto.AccountResponse;
import com.spring.api.entity.Account;
import com.spring.api.entity.User;
import com.spring.api.repo.AccountRepository;
import com.spring.api.repo.UserRepo;

@Service
public class AccountService {
	
	 @Autowired
	    private AccountRepository accountRepository;

	    @Autowired
	    private UserRepo userRepository;

	    public AccountResponse createAccount(AccountRequest request, String email) {
	        User user = userRepository.findByEmail(email).orElseThrow();

	        Account account = new Account();
	        account.setAccountNumber(request.getAccountNumber());
	        account.setBankName(request.getBankName());
	        account.setAccountType(request.getAccountType());
	        account.setUser(user);
	        account.setBalance(request.getBalance());

	        return new AccountResponse(accountRepository.save(account));
	    }

	    public List<AccountResponse> getAccounts(String email) {
	        User user = userRepository.findByEmail(email).orElseThrow();
	        return accountRepository.findByUser(user)
	                .stream()
	                .map(AccountResponse::new)
	                .collect(Collectors.toList());
	    }
	    public List<AccountBalanceResponse> getAccountBalances(String userEmail) {
	        User user = userRepository.findByEmail(userEmail)
	                            .orElseThrow(() -> new RuntimeException("User not found"));

	        List<Account> accounts = accountRepository.findByUser(user);

	        return accounts.stream()
	            .map(acc -> new AccountBalanceResponse(acc.getId(), acc.getBalance()))
	            .collect(Collectors.toList());
	    }

}

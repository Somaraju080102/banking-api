package com.spring.api.dto;

import java.math.BigDecimal;

import com.spring.api.entity.Account;

public class AccountResponse {
    private Long id;
    private String accountNumber;
    private String bankName;
    private String accountType;
    private BigDecimal balance;

    public AccountResponse(Account acc) {
        this.id = acc.getId();
        this.accountNumber = acc.getAccountNumber();
        this.bankName = acc.getBankName();
        this.accountType = acc.getAccountType();
        this.balance=acc.getBalance();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

    

}

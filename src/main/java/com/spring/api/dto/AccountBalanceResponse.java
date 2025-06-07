package com.spring.api.dto;

import java.math.BigDecimal;

public class AccountBalanceResponse {
	
	 private Long accountId;
	    private BigDecimal balance;

	    public AccountBalanceResponse(Long accountId, BigDecimal balance) {
	        this.accountId = accountId;
	        this.balance = balance;
	    }

		public Long getAccountId() {
			return accountId;
		}

		public void setAccountId(Long accountId) {
			this.accountId = accountId;
		}

		public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
	    

}

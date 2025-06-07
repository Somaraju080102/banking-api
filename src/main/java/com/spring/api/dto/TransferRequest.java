package com.spring.api.dto;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.DecimalMin;

public class TransferRequest {
	
	 @NotNull
	    private Long senderAccountId;

	    @NotNull
	    private Long receiverAccountId;

	    @NotNull
	    @DecimalMin(value = "0.01", message = "Amount must be positive")
	    private BigDecimal amount;

		public Long getSenderAccountId() {
			return senderAccountId;
		}

		public void setSenderAccountId(Long senderAccountId) {
			this.senderAccountId = senderAccountId;
		}

		public Long getReceiverAccountId() {
			return receiverAccountId;
		}

		public void setReceiverAccountId(Long receiverAccountId) {
			this.receiverAccountId = receiverAccountId;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	    
	    
}

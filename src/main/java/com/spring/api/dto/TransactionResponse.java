package com.spring.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.spring.api.enums.TransactionStatus;
import com.spring.api.enums.TransactionType;

public class TransactionResponse {
	
	private Long id;
    private Long senderAccountId;
    private Long receiverAccountId;
    private BigDecimal amount;
    private TransactionStatus status;
    private TransactionType type;
    private LocalDateTime createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
    
    
}

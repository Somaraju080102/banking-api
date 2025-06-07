package com.spring.api.service;

import java.util.List;

import com.spring.api.dto.TransactionResponse;
import com.spring.api.dto.TransferRequest;

public interface TransactionService {
    TransactionResponse transfer(TransferRequest request, String currentUserEmail);
    List<TransactionResponse> getTransactionsForUser(String currentUserEmail);
}

package com.spring.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.dto.TransactionResponse;
import com.spring.api.dto.TransferRequest;
import com.spring.api.entity.Account;
import com.spring.api.entity.Transaction;
import com.spring.api.entity.User;
import com.spring.api.enums.TransactionStatus;
import com.spring.api.enums.TransactionType;
import com.spring.api.repo.AccountRepository;
import com.spring.api.repo.TransactionRepository;
import com.spring.api.repo.UserRepo;



@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountRepository accountRepo;
    
    @Autowired
    private ExternalBankClient externalBankClient;
    
    @Autowired
    private UserRepo userRepo;

   
        // Validate sender matches current user or has permission
        // Validate accounts exist
        // Validate sufficient funds, consent etc (if applicable)
    	@Override
    	public TransactionResponse transfer(TransferRequest request, String currentUserEmail) {
    	    User senderUser = userRepo.findByEmail(currentUserEmail)
    	        .orElseThrow(() -> new RuntimeException("User not found"));

    	    // Get sender account
    	    List<Account> accounts = accountRepo.findByUser(senderUser);
    	    if (accounts.isEmpty()) throw new RuntimeException("Sender account not found");

    	    Account senderAccount = accounts.get(0);

    	    // 🚫 Prevent transfer to same account
    	    if (senderAccount.getId().equals(request.getReceiverAccountId())) {
    	        throw new RuntimeException("Cannot transfer to the same account");
    	    }

    	    // Validate receiver
    	    Account receiverAccount = accountRepo.findById(request.getReceiverAccountId())
    	        .orElse(null); // could be null (external)

    	    // 💰 Validate sufficient balance
    	    if (senderAccount.getBalance().compareTo(request.getAmount()) < 0) {
    	        throw new RuntimeException("Insufficient balance");
    	    }

    	    // Start transaction object
    	    Transaction txn = new Transaction();
    	    txn.setSenderAccountId(senderAccount.getId());
    	    txn.setReceiverAccountId(request.getReceiverAccountId());
    	    txn.setAmount(request.getAmount());
    	    txn.setCreatedAt(LocalDateTime.now());
    	    txn.setType(TransactionType.TRANSFER);
    	    txn.setUser(senderUser); // important for DB constraint

    	    // ✅ Internal transfer
    	    if (receiverAccount != null) {
    	        // Deduct + Credit
    	        senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
    	        receiverAccount.setBalance(receiverAccount.getBalance().add(request.getAmount()));

    	        accountRepo.save(senderAccount);
    	        accountRepo.save(receiverAccount);

    	        txn.setStatus(TransactionStatus.SUCCESS);
    	    } else {
    	        // 🌐 External transfer via mock bank
    	        boolean success = externalBankClient.sendMoneyToExternalBank(request);
    	        if (success) {
    	            senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
    	            accountRepo.save(senderAccount);
    	            txn.setStatus(TransactionStatus.SUCCESS);
    	        } else {
    	            txn.setStatus(TransactionStatus.FAILED);
    	        }
    	    }

    	    txn = transactionRepo.save(txn);

    	    // Build response
    	    TransactionResponse response = new TransactionResponse();
    	    BeanUtils.copyProperties(txn, response);
    	    return response;
    	}

    

    @Override
    public List<TransactionResponse> getTransactionsForUser(String currentUserEmail) {
        // Fetch user accounts first, then transactions from those accounts
        // Or filter transactions where sender or receiver accounts belong to user

        // Simple example (expand as per your model):
        List<Transaction> txns = transactionRepo.findBySenderAccountUserEmail(currentUserEmail);
        return txns.stream().map(txn -> {
            TransactionResponse res = new TransactionResponse();
            BeanUtils.copyProperties(txn, res);
            return res;
        }).collect(Collectors.toList());
    }
}

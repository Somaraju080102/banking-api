package com.spring.api.service;

import java.time.LocalDateTime;
import java.util.List;
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
    private UserRepo userRepo;

    @Override
    public TransactionResponse transfer(TransferRequest request, String currentUserEmail) {
        // Validate sender matches current user or has permission
        // Validate accounts exist
        // Validate sufficient funds, consent etc (if applicable)
    	User senderUser = userRepo.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Account> senderAccounts = accountRepo.findByUser(senderUser);
        if (senderAccounts.isEmpty()) {
            throw new RuntimeException("Sender account not found");
        }
        Account senderAccount = senderAccounts.get(0);

        Account receiverAccount = accountRepo.findById(request.getReceiverAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        // Check balance
        if (senderAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Deduct from sender, add to receiver
        senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(request.getAmount()));

        // Save accounts
        accountRepo.save(senderAccount);
        accountRepo.save(receiverAccount);

        // Create transaction with SUCCESS status
        Transaction txn = new Transaction();
        txn.setSenderAccountId(senderAccount.getId());
        txn.setReceiverAccountId(receiverAccount.getId());
        txn.setAmount(request.getAmount());
        txn.setStatus(TransactionStatus.SUCCESS);
        txn.setType(TransactionType.TRANSFER);
        txn.setCreatedAt(LocalDateTime.now());

        // Set the user here
        txn.setUser(senderUser);

        txn = transactionRepo.save(txn);

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

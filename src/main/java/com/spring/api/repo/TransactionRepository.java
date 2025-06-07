package com.spring.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.api.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
    @Query("SELECT t FROM Transaction t WHERE t.senderAccountId IN " +
            "(SELECT a.id FROM Account a WHERE a.user.email = :email)")
     List<Transaction> findBySenderAccountUserEmail(@Param("email") String email);
     // Optionally include received transactions as well
     @Query("SELECT t FROM Transaction t WHERE t.senderAccountId IN " +
            "(SELECT a.id FROM Account a WHERE a.user.email = :email) " +
            "OR t.receiverAccountId IN " +
            "(SELECT a.id FROM Account a WHERE a.user.email = :email)")
     List<Transaction> findAllUserRelatedTransactions(@Param("email") String email);

	
	

}

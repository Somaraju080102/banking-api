package com.spring.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.api.entity.Account;
import com.spring.api.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
    List<Account> findByUser(User user);
    


}

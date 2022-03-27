package com.edmwat.metropol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmwat.metropol.models.Account;

@Repository 
public interface AccountsRepo extends JpaRepository<Account,Long> {
	void deleteById(String clientId);
	List<Account> findByClientId(String clientId);

}

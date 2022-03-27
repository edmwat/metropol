package com.edmwat.metropol.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edmwat.metropol.models.Account;
import com.edmwat.metropol.repositories.AccountsRepo;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class AccountsService {
	
	private final AccountsRepo accountsRepo;
	
	public List<Account> getClientAccounts(String clientId){
		return accountsRepo.findByClientId(clientId);
	}
	
	public void addNewAccount(Account account) {
		this.accountsRepo.save(account);		
	}
	
	public void deleteAccount(Long accountId) {
		this.accountsRepo.deleteById(accountId);
	}

}

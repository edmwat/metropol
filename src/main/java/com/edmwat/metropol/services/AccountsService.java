package com.edmwat.metropol.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edmwat.metropol.models.Account;

@Service 
public class AccountsService {
	
	public List<Account> getClientAccounts(String clientId){
		return List.of(new Account());
	}
	
	public void addNewClientAccount(Account account) {
		
	}
	
	public void deleteClientAccount(String clientId) {
		
	}

}

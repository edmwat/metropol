package com.edmwat.metropol.apis;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmwat.metropol.models.Account;

@RestController 
@RequestMapping("/accounts")
public class AccountsController {
	
	@GetMapping("/{clientId}")
	public List<Account> getClientAccounts(){
		return List.of(new Account("1","dd","dd","edmwat"));
	}
	
	@PostMapping("/new")
	public void addNewClientAccount() {
		
	}
	
	@DeleteMapping("/clientId")
	public void deleteClientAccount() {
		
	}

}

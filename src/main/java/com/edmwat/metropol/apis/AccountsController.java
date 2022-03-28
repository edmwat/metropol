package com.edmwat.metropol.apis;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmwat.metropol.models.Account;
import com.edmwat.metropol.services.AccountsService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/accounts")
@AllArgsConstructor 
public class AccountsController {
	
	private final AccountsService accountService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Account>> getClientAccounts(){
		String clientId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		return ResponseEntity.ok(accountService.getClientAccounts(clientId));
	}
	
	@PostMapping("/add")
	public void addNewAccount(@RequestBody Account account) {
		this.accountService.addNewAccount(account);
	}
	
	@DeleteMapping("/{accountId}")
	public void deleteClientAccount(@PathVariable Long accountId) {
		this.accountService.deleteAccount(Long.valueOf(accountId) );
	}

}

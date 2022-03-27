package com.edmwat.metropol.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmwat.metropol.models.AuthenticationRequest;
import com.edmwat.metropol.models.AuthenticationResponse;
import com.edmwat.metropol.models.LoggedInUser;
import com.edmwat.metropol.services.UsersService;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
public class UsersController {
	
	private final UsersService userService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationObject(@RequestBody AuthenticationRequest authReq){
		
		return ResponseEntity.ok(userService.createAuthenticationObject(authReq));
	}
	@GetMapping("/authenticatedPrincipal")
	public ResponseEntity<LoggedInUser> getLoggedInUser() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String s ="";
		
		return ResponseEntity.ok(new LoggedInUser(name));
	}
 
}

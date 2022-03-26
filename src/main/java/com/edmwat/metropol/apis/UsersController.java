package com.edmwat.metropol.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmwat.metropol.models.AuthenticationRequest;
import com.edmwat.metropol.models.AuthenticationResponse;
import com.edmwat.metropol.services.UsersService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/authenticate")
@AllArgsConstructor 
public class UsersController {
	
	private final UsersService userService;
	
	@PostMapping
	public ResponseEntity<AuthenticationResponse> createAuthenticationObject(@RequestBody AuthenticationRequest authReq){
		
		return ResponseEntity.ok(userService.createAuthenticationObject(authReq));
	}
 
}

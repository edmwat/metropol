package com.edmwat.metropol.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.edmwat.metropol.models.AuthenticationRequest;
import com.edmwat.metropol.models.AuthenticationResponse;
import com.edmwat.metropol.utils.TokenFactory;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class AuthenticationService {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final TokenFactory tokenFactory;
	
	public AuthenticationResponse createAuthenticationObject(AuthenticationRequest authReq) {
		try {
			authenticationManager
				.authenticate(
					new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword()));
		}catch(BadCredentialsException e) {
			System.out.println(e.getMessage());
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
		
		String token = tokenFactory.createToken(userDetails);
		
		return new AuthenticationResponse(token);
		
	}

}

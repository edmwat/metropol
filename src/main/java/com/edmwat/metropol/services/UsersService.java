package com.edmwat.metropol.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.edmwat.metropol.models.AuthenticationRequest;
import com.edmwat.metropol.models.AuthenticationResponse;
import com.edmwat.metropol.exceptions.AuthenticationException;
import com.edmwat.metropol.models.AppUser;
import com.edmwat.metropol.repositories.UsersRepository;
import com.edmwat.metropol.utils.TokenFactory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor 
@Slf4j 
public class UsersService {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final TokenFactory tokenFactory;
	private final UsersRepository userRepo;
	
	public AuthenticationResponse createAuthenticationObject(AuthenticationRequest authReq) {
		log.info("Create authentication object!");
		String token="";
		try {
			authenticationManager
				.authenticate(
					new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword()));
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
			
			token = tokenFactory.createToken(userDetails);
		}catch(Exception e) { 
			log.error(e.getMessage());
			throw new AuthenticationException("Bad Credentials", 401);
		}
		
		
		return new AuthenticationResponse(token, authReq.getUsername());		
	}
	
	public void createSystemUsers(AppUser user) {
		userRepo.save(user);
	}

}

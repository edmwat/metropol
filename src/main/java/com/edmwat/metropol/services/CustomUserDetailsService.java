package com.edmwat.metropol.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.edmwat.metropol.repositories.UsersRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UsersRepository usersRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersRepo.findByUsername(username).get();
	}		
}

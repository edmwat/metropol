package com.edmwat.metropol;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edmwat.metropol.models.Account;
import com.edmwat.metropol.models.AppUser;
import com.edmwat.metropol.models.Card;
import com.edmwat.metropol.services.AccountsService;
import com.edmwat.metropol.services.CardsService;
import com.edmwat.metropol.services.UsersService;

@SpringBootApplication
public class MetropolApplication {
	@Autowired 
	private  PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MetropolApplication.class, args);
	}		
	
	@Bean 
	CommandLineRunner run(UsersService service, AccountsService accService, CardsService cardService) {
		return args ->{
			service.createSystemUsers(new AppUser(1l,"metropol1@gmail.com",passwordEncoder.encode("metropol1"), Collections.singletonList(new SimpleGrantedAuthority("USER"))));
			service.createSystemUsers(new AppUser(2l,"metropol2@gmail.com",passwordEncoder.encode("metropol"),Collections.singletonList(new SimpleGrantedAuthority("USER"))));
			
			accService.addNewAccount(new Account(1L,1234L,"KIMEQTY","metropol2@gmail.com"));
			accService.addNewAccount(new Account(2L,4321L,"KIMKEST","metropol1@gmail.com"));
			
			cardService.addNewCard(new Card(1l,"credit card",1l,"Virtual"));
			cardService.addNewCard(new Card(2l,"Debit card",2l,"physical"));
		}; 
	}
}
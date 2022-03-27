package com.edmwat.metropol.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class TokenFactory {
	
	final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
	final JWTVerifier verifier = JWT.require(algorithm).build();
	
	public String createToken(UserDetails userDetails) {
		
		String access_token = JWT.create()
				.withSubject(userDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + (60 *60 * 1000)))
				.withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		
		return access_token;
	}

	public DecodedJWT decodeToken(String token)  {
		DecodedJWT decodedJwt = null;
		try {
			decodedJwt = verifier.verify(token);	
			//return decodedJwt;	
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return decodedJwt;		
	}
	
	public String extractUsername(String token) {
		String subject = "";
		try{
			subject = decodeToken(token).getSubject();
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return subject;
	}
	public boolean isExpired(String token) {
		
		DecodedJWT decodedJwt = null;
		try {
			decodedJwt = verifier.verify(token);	
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return decodedJwt != null ? false : true; 
	}
	
	public Collection<SimpleGrantedAuthority> extractRoles(){
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		return authorities;
	}
}

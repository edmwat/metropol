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

@Service
public class TokenFactory {
	
	final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
	final JWTVerifier verifier = JWT.require(algorithm).build();
	
	public String createToken(UserDetails userDetails) {
		
		String access_token = JWT.create()
				.withSubject(userDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + (10 *60 * 1000)))
				.withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		
		return access_token;
	}

	public DecodedJWT decodeToken(String token) {
		
		DecodedJWT decodedJwt = verifier.verify(token);	
		return decodedJwt;	
	}
	
	public String extractUsername(String token) {
		return decodeToken(token).getSubject();
	}
	
	public Collection<SimpleGrantedAuthority> extractRoles(){
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		return authorities;
	}
}

package com.edmwat.metropol.filters;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edmwat.metropol.services.CustomUserDetailsService;
import com.edmwat.metropol.utils.TokenFactory;

import lombok.AllArgsConstructor;

@Component 
@AllArgsConstructor 
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final TokenFactory tokenFactory;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authoriationHeader = request.getHeader("Authorization");
		String username ="";
		
		if(authoriationHeader != null && authoriationHeader.startsWith("Bearer ")) {
			String jwt = authoriationHeader.substring(7);
			username = tokenFactory.extractUsername(jwt);
		}	
		if(Objects.nonNull(username) && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);			
		}	
		filterChain.doFilter(request,response);
	}

}

package com.edmwat.metropol.filters;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edmwat.metropol.exceptions.AuthenticationException;
import com.edmwat.metropol.models.ErrorResponse;
import com.edmwat.metropol.services.CustomUserDetailsService;
import com.edmwat.metropol.utils.TokenFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;

@Component 
@AllArgsConstructor 
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final TokenFactory tokenFactory;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String authoriationHeader = request.getHeader("Authorization");
			String username ="";
			
				if(authoriationHeader != null && authoriationHeader.startsWith("Bearer ")) {
					String jwt = authoriationHeader.substring(7);
					username = tokenFactory.extractUsername(jwt);
				}else {
					throw new AuthenticationException("not authenticated", 401);
				}
				if(Objects.nonNull(username) && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null){
					UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource());
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);			
				}	
			
			filterChain.doFilter(request,response);
		}catch(AuthenticationException e) {
			//throw e;
			//System.out.println(e.getMessage());
			ErrorResponse er = new ErrorResponse();
			er.setMessage(e.getMessage());
			er.setErrorCode(e.getStatusCode());
			response.getWriter().write(convertObjectToJson(er));
		}
		
	}
	public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}

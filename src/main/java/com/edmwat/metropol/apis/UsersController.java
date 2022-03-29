package com.edmwat.metropol.apis;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmwat.metropol.models.AuthenticationRequest;
import com.edmwat.metropol.models.AuthenticationResponse;
import com.edmwat.metropol.models.ErrorResponse;
import com.edmwat.metropol.models.LoggedInUser;
import com.edmwat.metropol.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
public class UsersController {
	
	private final UsersService userService;
	
	@PostMapping("/authenticate")
	public void createAuthenticationObject(@RequestBody AuthenticationRequest authReq, HttpServletResponse response){
		AuthenticationResponse ar=null;
		
		try {
			String s = "";
			ar = userService.createAuthenticationObject(authReq);
			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(ar));
			
		}catch(Exception e) {
			try {
				ErrorResponse er = new ErrorResponse();
				er.setMessage(e.getMessage());
				er.setErrorCode(401);
				response.setStatus(401);
				response.getWriter().write(new ObjectMapper().writeValueAsString(er));
			}catch(Exception ex) {
				
			}
			
		}		
		//if(ar != null)
			//return ResponseEntity.ok(ar);
		
	}
	@GetMapping("/authenticatedPrincipal")
	public ResponseEntity<LoggedInUser> getLoggedInUser() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String s ="";
		
		return ResponseEntity.ok(new LoggedInUser(name));
	}
 
}

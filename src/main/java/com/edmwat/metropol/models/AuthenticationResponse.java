package com.edmwat.metropol.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class AuthenticationResponse {
	private String jwt;

}

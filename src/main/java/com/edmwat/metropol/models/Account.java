package com.edmwat.metropol.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
	@Id
	private long id;
	private long iban;
	private String bicSwift;
	private String clientId;
	
	public Account(long iban, String bicSwift, String clientId){
		this.iban = iban;
		this.bicSwift= bicSwift;
		this.clientId = clientId;
	}

}

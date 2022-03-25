package com.edmwat.metropol.models;

import javax.persistence.Entity;
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
	private String accountId;
	private String iban;
	private String bicSwift;
	private String clientId;

}

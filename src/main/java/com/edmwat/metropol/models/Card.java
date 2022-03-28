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
public class Card {
	@Id
	private long id;
	private String alias;
	private long accountId;
	private String cardType;
}

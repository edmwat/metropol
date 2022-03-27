package com.edmwat.metropol;

import java.util.List;

import com.edmwat.metropol.models.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class AccountsWrapper {
	private List<Account> accountList;

}

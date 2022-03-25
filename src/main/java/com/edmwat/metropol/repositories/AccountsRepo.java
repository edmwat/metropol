package com.edmwat.metropol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmwat.metropol.models.Account;

@Repository 
public interface AccountsRepo extends JpaRepository<Account,Long> {

}

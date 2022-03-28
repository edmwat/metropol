package com.edmwat.metropol.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edmwat.metropol.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	List<Card> findByAccountId(String accountId);
}

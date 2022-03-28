package com.edmwat.metropol.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edmwat.metropol.models.Card;
import com.edmwat.metropol.repositories.CardRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsService {
	
	private final CardRepository cardRepo;
	
	public List<Card> getAccountCards(long accountId){
		return cardRepo.findByAccountId(accountId);
	}
	
	public void addNewCard(Card card) {
		this.cardRepo.save(card);		
	}
	
	public void deleteCard(Long cardId) {
		this.cardRepo.deleteById(cardId);
	}
}

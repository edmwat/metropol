package com.edmwat.metropol.apis;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmwat.metropol.models.Card;
import com.edmwat.metropol.services.CardsService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/cards")
@AllArgsConstructor 
public class CardsController {
	
	private final CardsService cardService;
	
	@GetMapping("/{accountId}")
	public ResponseEntity<List<Card>> getAccountCards(@PathVariable String accountId){	
		return ResponseEntity.ok(cardService.getAccountCards(accountId));
	}
	
	@PostMapping("/add")
	public void addNewCard(@RequestBody Card card) {
		this.cardService.addNewCard(card);
	}
	
	@DeleteMapping("/{cardId}")
	public void deleteCard(@PathVariable Long cardId) {
		this.cardService.deleteCard(Long.valueOf(cardId) );
	}

}

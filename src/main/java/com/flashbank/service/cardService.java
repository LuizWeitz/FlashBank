package com.flashbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flashbank.model.Card;
import com.flashbank.repository.cardRepository;

@Service
public class cardService {

	@Autowired
	private cardRepository cardRepository;
	
	// method of unlock card
	
	public String unlockCard(Card card) throws Exception {

		String status;

		try {

			String password = new BCryptPasswordEncoder().encode(card.getPassword());

			card.setPassword(password);

			card.setActive(true);

			cardRepository.save(card);

			status = "Cartão desbloquedo com SUCESSO :D";

		} catch (Exception e) {

			status = "Erro ao desbloquear cartão :(";

		}
		
		return status;

	}
	
	// method of search all cards
	
	public List<Card> searchAllCards(){
		
	List<Card> cards = (List<Card>) cardRepository.findAll();
	
	return cards;
		
	}

}

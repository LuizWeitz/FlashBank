package com.flashbank.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.flashbank.model.Card;

public interface cardRepository extends CrudRepository<Card, Long>{
	
	// method of remove money of the limit_money of card
	
	@Transactional
	@Modifying
	@Query(value = "update card set limit_money = ((select limit_money from card where number = ?1) - ?2) where number = ?3", nativeQuery = true)
	void removeMoneyOfLimit(String number_card, float removeCredit, String number_card2);
	
	


	
}

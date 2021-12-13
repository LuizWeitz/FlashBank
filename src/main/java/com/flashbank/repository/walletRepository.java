package com.flashbank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.flashbank.model.Wallet;

public interface walletRepository extends CrudRepository<Wallet, Long>{
	
	// method of add amount for who will receive the payment

	@Transactional
	@Modifying
	@Query(value = "update wallet set amount = ((select amount from wallet where account_id = ?1) + ?2) where account_id = ?3", nativeQuery = true)
	void addAmountPayment(Long account_id, float addAmmount, Long account_id2);
	
	// method of remove amount 

	@Transactional
	@Modifying
	@Query(value = "update wallet set amount = ((select amount from wallet where account_id = ?1) - ?2) where account_id = ?3", nativeQuery = true)
	void removeAmountPayment(Long account_id, float removeAmmount, Long account_id2);
	
	
	
}


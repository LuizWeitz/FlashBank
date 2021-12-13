package com.flashbank.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flashbank.model.Account;

@Repository
public interface accountRepository extends CrudRepository<Account, Long> {
	
	// find user by document

	@Query("SELECT a FROM Account a WHERE a.document = ?1")
	Account findUserByLogin(String document);
	
	// update token user by document
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update account set token = ?1 where document = ?2")
	void updateTokenAccount(String token, String document);



	
	
}


// Copyright © FlashBank
// All rights reserved
// Code by Luiz Weitz

package com.flashbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashbank.model.Account;
import com.flashbank.service.accountService;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/account")
@RestController
public class accountController {

	@Autowired
	private accountService accountService;

	@GetMapping(value = "searchbyid/{id}")
	public ResponseEntity<Account> searchByIdAccount(@PathVariable(value = "id") Long id) throws Exception {

		Account account = accountService.searchById(id);

		if (account == null) {

			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<Account>(account, HttpStatus.OK);

		}

	}

	@PostMapping(value = "/new")
	public ResponseEntity<String> newUser(@RequestBody Account account) throws Exception {

		String status = accountService.newAccount(account);

		return new ResponseEntity<String>(status, HttpStatus.CREATED);

	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Account>> searchAllAccounts() {

		List<Account> allAccount = (List<Account>) accountService.searchAllAccounts();

		return new ResponseEntity<List<Account>>(allAccount, HttpStatus.OK);

	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> updateAccount(@RequestBody Account account) throws Exception {

		String status = accountService.updateAccount(account);

		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable(value = "id") Long id) throws Exception {

		String status = accountService.deleteAccount(id);

		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

}

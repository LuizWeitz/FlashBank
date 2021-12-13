// Copyright © FlashBank
// All rights reserved
// Code by Luiz Weitz

package com.flashbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashbank.model.Wallet;
import com.flashbank.service.walletService;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/wallet")
@RestController
public class walletController {

	@Autowired
	private walletService walletService;

	@PostMapping(value = "/new")
	public ResponseEntity<String> newWallet(@RequestBody Wallet wallet) throws Exception {

		String status = walletService.newWallet(wallet);

		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Wallet>> searchAll() {

		List<Wallet> allWallets = walletService.searchAllWallets();

		return new ResponseEntity<List<Wallet>>(allWallets, HttpStatus.OK);

	}

	@GetMapping(value = "/searchbyid/{id}")
	public ResponseEntity<Wallet> searchbyid(@PathVariable(value = "id") Long id) throws Exception {

		Wallet wallet = walletService.searchById(id);

		if (wallet == null) {

			return new ResponseEntity<Wallet>(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);

	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> updateWallet(@RequestBody Wallet wallet) throws Exception {

		String status = walletService.updateWallet(wallet);

		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

}

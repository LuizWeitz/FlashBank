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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashbank.model.Card;
import com.flashbank.service.cardService;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/card")
@RestController
public class cardController {

	@Autowired
	private cardService cardService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<Card>> searchAll() {

		List<Card> allCards = cardService.searchAllCards();

		return new ResponseEntity<List<Card>>(allCards, HttpStatus.OK);

	}

	@PutMapping(value = "/unlock")
	public ResponseEntity<String> unlockCard(@RequestBody Card card) throws Exception {

		String status = cardService.unlockCard(card);

		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

}

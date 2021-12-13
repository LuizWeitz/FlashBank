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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashbank.model.Payment;
import com.flashbank.service.paymentService;


@CrossOrigin(origins = "*")
@RequestMapping(value = "/payment")
@RestController
public class paymentController {

	@Autowired
	private paymentService paymentService;

	@PostMapping(value = "/debit/{number_card}")
	public ResponseEntity<String> newPaymentDebit(@RequestBody Payment payment,
			@PathVariable(value = "number_card") int number) throws Exception {

	    String status =	paymentService.newPaymentDebit(payment, number);
		
		return new ResponseEntity<String>(status, HttpStatus.CREATED);

	}


	@GetMapping(value = "/all")
	public ResponseEntity<List<Payment>> searchAllPayments() {

		List<Payment> allPayments = paymentService.sarchAllPayments(); 
				
				
		return new ResponseEntity<List<Payment>>(allPayments, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/credit/{number_card}")
	public ResponseEntity<String> newPaymentCredit(@RequestBody Payment payment,
			@PathVariable(value = "number_card") int number) throws Exception {
		
       	String number_card = Integer.toString(number);

		String status = paymentService.newPaymentCredit(payment, number_card);
		
		return new ResponseEntity<String>(status, HttpStatus.CREATED);

	}

}

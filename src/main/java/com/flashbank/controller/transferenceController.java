package com.flashbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashbank.model.Transference;
import com.flashbank.service.transferenceService;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/transference")
@RestController
public class transferenceController {
	
	@Autowired
	private transferenceService transferenceService;
	
	@PostMapping("/new")
	public ResponseEntity<String> newTransference(@RequestBody Transference transference) throws Exception {
		
		String status = transferenceService.newTransference(transference);
		
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
		

	}

}

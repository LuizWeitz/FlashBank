package com.flashbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class FlashbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashbankApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("159753486123"));
		
	 
	}

	@GetMapping(value = "/")
	public ResponseEntity<String> index() {

		return (ResponseEntity.ok("Deploy successfully :D"));

	}

}

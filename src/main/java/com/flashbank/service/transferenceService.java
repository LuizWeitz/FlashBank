package com.flashbank.service;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flashbank.model.Transference;
import com.flashbank.repository.transferenceRepository;

@Service
public class transferenceService {
	
	@Autowired
	transferenceRepository transferenceRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String newTransference(Transference transference) throws Exception {
		
		String status;
		
		float amount = transference.getAmount();
		
	// String of test, put why add token security	
	   String	account_id_of_payer = "";
		
		Float ammount_of_payer = jdbcTemplate.queryForObject(
				"select amount from wallet where account_id = '" + account_id_of_payer + "'", Float.class);
		
		Boolean active = jdbcTemplate.queryForObject(
				"select active from card where account_id = '" + account_id_of_payer + "'", Boolean.class);
		
		if(ammount_of_payer >= amount && active == true) {

		String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

		String host_ip = InetAddress.getLocalHost().getHostAddress();

		String host_name = InetAddress.getLocalHost().getHostName();

		String host = date_time + " " + host_name + " " + host_ip;
		
		transference.setCreated_in_and_by(host);
	
		
		if(transference.getDescription() == null || transference.getDescription() == "") {
			
			transference.setDescription("Sem Descrição");
			
		}
		
		String password_account_transference = transference.getPassword_account();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String email = "";
		
		String password_account = jdbcTemplate
				.queryForObject("select password from account where email = '" + email + "'", String.class);

		boolean valid_password = passwordEncoder.matches(password_account_transference, password_account );
		
		if(valid_password ==  true) {
			
	
			transferenceRepository.addAmountPayment(null, amount, null);
			
			transferenceRepository.removeAmountPayment(null, amount, null);
			
			transference.setStatus("Aprovado");
			
			transferenceRepository.save(transference);
			
			status = "Transferência realizada com SUCESSO :D";
			
			
		} else {
			
			status = "Senha errada, tente novamente :(";
			
		}
		
		
		} else {
			
			status = "Sem fundos para realizar a transação :(";
			
			
		}
		
		
		return status;
		
	}
	
	
	
	
    

}

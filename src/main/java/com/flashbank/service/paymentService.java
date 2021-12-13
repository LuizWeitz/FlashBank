package com.flashbank.service;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flashbank.model.Payment;
import com.flashbank.model.Portion;
import com.flashbank.repository.portionRepository;
import com.flashbank.repository.cardRepository;
import com.flashbank.repository.paymentRepository;
import com.flashbank.repository.walletRepository;


@Service
public class paymentService {
	
	@Autowired
	private paymentRepository paymentRepository;

	@Autowired
	private walletRepository walletRepository;
	
	@Autowired
	private cardRepository cardRepository;
	 
	@Autowired
	private portionRepository portionRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Payment> sarchAllPayments(){
		
		List<Payment> allPayments = (List<Payment>) paymentRepository.findAll();

		return allPayments;
		
		
	}
	
	// method of new payment debit
	
	public String newPaymentDebit(Payment payment, int number) throws Exception {
		
		
		String status;

		Float amount = payment.getAmount();

		String document_of_receive = payment.getDestiny();

		String document_of_payer = jdbcTemplate
				.queryForObject("select document from card where number = '" + number + "'", String.class);

		Long account_id_of_payer = jdbcTemplate
				.queryForObject("select id from account where document = '" + document_of_payer + "'", Long.class);

		Float ammount_of_payer = jdbcTemplate.queryForObject(
				"select amount from wallet where account_id = '" + account_id_of_payer + "'", Float.class);

		Boolean active = jdbcTemplate.queryForObject(
				"select active from card where account_id = '" + account_id_of_payer + "'", Boolean.class);

		if (ammount_of_payer >= amount) {

			if (active == true) {
				
				payment.setPayer(document_of_payer);

				payment.setType_of_payment("dé");

				payment.setPortions(1);

				Long account_id_of_receive = jdbcTemplate.queryForObject(
						"select id from account where document = '" + document_of_receive + "'", Long.class);

				String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

				String host_ip = InetAddress.getLocalHost().getHostAddress();

				String host_name = InetAddress.getLocalHost().getHostName();

				String host = date_time + " " + host_name + " " + host_ip;

				payment.setCreated_in_and_by(host);

				String password_card_payment = payment.getPassword_card();

				String password_card = jdbcTemplate
						.queryForObject("select password from card where number = '" + number + "'", String.class);

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

				boolean valid_password = passwordEncoder.matches(password_card_payment, password_card);

				if (valid_password == true) {
					

					walletRepository.addAmountPayment(account_id_of_receive, amount, account_id_of_receive);

					walletRepository.removeAmountPayment(account_id_of_payer, amount, account_id_of_payer);

					payment.setPassword_card("ok");

					payment.setStatus("Aprovado");

					paymentRepository.save(payment);
					
					

					status = "INCRÍVEL, pagamento realizado :D";
					

				} else {

					status = "Senha errada, tente novamente :(";

				}
			} else {

				status = "Cartão bloqueado";
				
			}

		} else {

			status = "Sem saldo para realizar o pagamento :(";

		}

		
		return status;
		
	}
	
	// new payment credit
	
	public String newPaymentCredit(Payment payment, String number) throws Exception {
		
		String status = "";

		Float ammount = payment.getAmount();

		int portions = payment.getPortions();

		String document_of_payer = jdbcTemplate
				.queryForObject("select document from card where number = '" + number + "'", String.class);

		Long account_id_of_payer = jdbcTemplate
				.queryForObject("select id from account where document = '" + document_of_payer + "'", Long.class);

		Float ammount_of_card_of_payer = jdbcTemplate
				.queryForObject("select limit_money from card where number = '" + number + "'", Float.class);

		Boolean active = jdbcTemplate.queryForObject(
				"select active from card where account_id = '" + account_id_of_payer + "'", Boolean.class);

		if (ammount_of_card_of_payer >= ammount) {

			if (active == true) {

				payment.setPayer(document_of_payer);

				payment.setType_of_payment("credit");

				String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

				String host_ip = InetAddress.getLocalHost().getHostAddress();

				String host_name = InetAddress.getLocalHost().getHostName();

				String host = date_time + " " + host_name + " " + host_ip;

				payment.setCreated_in_and_by(host);

				String password_card_payment = payment.getPassword_card();

				String password_card = jdbcTemplate
						.queryForObject("select password from card where number = '" + number + "'", String.class);

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

				boolean valid_password = passwordEncoder.matches(password_card_payment, password_card);

				if (valid_password == true) {

					// method of remove ammount of payment of credit card

					cardRepository.removeMoneyOfLimit(number, ammount, number);
					
					String password = new BCryptPasswordEncoder().encode(payment.getPassword_card());

					payment.setPassword_card(password);

					payment.setStatus("Aprovado");

					paymentRepository.save(payment);

					Float value_of_portion = ammount / portions;

					Long id_payment = payment.getId();

					DateFormat date_format = new SimpleDateFormat("MM/yyyy");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MONTH, 1);
					Date date = calendar.getTime();

					for (int i = 1; i <= portions; i++) {

						String name_local = "test";

						String date_validity = date_format.format(date);

						Portion portion = new Portion();

						portion.setEstablishment(name_local);
						portion.setNumber(i);
						portion.setPay(false);
						portion.setValidity(date_validity);
						portion.setPayment_id(id_payment);
						portion.setValue(value_of_portion);

						portionRepository.save(portion);

						calendar.add(Calendar.MONTH, 1);
						date = calendar.getTime();

					}

					status = "INCRÍVEL, pagamento realizado :D";

				} else {

					status = "Senha incorreta, tente novamente :(";

				}
			} else {

				status = "Cartão bloqueado";
			}

		} else {

			status = "Sem saldo para realizar o pagamento :(";

		}
		return status;
		
		
	}
	

}

package com.flashbank.service;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashbank.model.Account;
import com.flashbank.model.Card;
import com.flashbank.model.Wallet;
import com.flashbank.repository.accountRepository;
import com.flashbank.repository.cardRepository;
import com.flashbank.repository.walletRepository;

@Service
public class accountService {

	@Autowired
	private accountRepository accountRepository;

	@Autowired
	private walletRepository walletRepository;

	@Autowired
	private cardRepository cardRepository;

	// method of created new account, card and wallet

	public String newAccount(Account account) throws Exception {

		String status;

		try {

			// block of security

			String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			String host_ip = InetAddress.getLocalHost().getHostAddress();

			String host_name = InetAddress.getLocalHost().getHostName();

			String host = date_time + " " + host_name + " " + host_ip;

			account.setCreated_in_and_by(host);

			// end of block security

			accountRepository.save(account);

			Long id = account.getId();

			String type_wallet = "Conta corrente";

			// block of created wallet

			Wallet wallet = new Wallet();

			wallet.setAccount_id(id);
			wallet.setId(id);
			wallet.setAmount(0);
			wallet.setType_wallet(type_wallet);
			wallet.setCreated_in_and_by(host);

			walletRepository.save(wallet);

			// block of created card

			Random random = new Random();

			// number generation for cod_security;

			int code_security = Math.abs(random.nextInt(100000));

			int number = Math.abs(random.nextInt(100000000));

			// method of good_thru, is for 24 months of good_thru

			DateFormat date_format = new SimpleDateFormat("MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 24);
			Date date = calendar.getTime();

			String good_thru = date_format.format(date);

			String type_of_card = "credit and debit";

			String name_of_card = account.getName();

			String document_of_card = account.getDocument();

			Card card = new Card();

			card.setAccount_id(id);
			card.setActive(false);
			card.setCod_security(code_security);
			card.setType(type_of_card);
			card.setGood_thru(good_thru);
			card.setDocument(document_of_card);
			card.setLimit_money(50);
			card.setName(name_of_card);
			card.setNumber(number);

			cardRepository.save(card);

			status = "Conta criada com SUCESSO :D";

		} catch (Exception e) {

			status = "Erro ao criar conta, por favor tente novamente :(";

		}

		return status;

	}

	// method of search all accounts

	public List<Account> searchAllAccounts() {

		List<Account> allAccounts = (List<Account>) accountRepository.findAll();

		return allAccounts;
	}

	// method of search account by id

	public Account searchById(Long id) throws Exception {

		try {

			Optional<Account> account = accountRepository.findById(id);

			return account.get();

		} catch (Exception e) {

			return null;

		}

	}

	// method of update account

	public String updateAccount(Account account) throws Exception {

		String status;

		try {

			String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			String host_ip = InetAddress.getLocalHost().getHostAddress();

			String host_name = InetAddress.getLocalHost().getHostName();

			String host = date_time + " " + host_name + " " + host_ip;

			account.setLast_update(host);

			accountRepository.save(account);

			status = "Conta editada com SUCESSO :D";

		} catch (Exception e) {

			status = "Erro ao editar conta, por favor tente novamente";

		}

		return status;
	}

	// method of delete account
	
	public String deleteAccount(Long id) throws Exception {

		String status;

		try {

			accountRepository.deleteById(id);

			status = "Conta deletada com sucesso!";

		} catch (Exception e) {

			status = "Erro ao deletar a conta, por favor tente novamente :(";

		}

		return status;

	}

}

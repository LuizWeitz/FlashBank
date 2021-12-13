package com.flashbank.service;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashbank.model.Wallet;
import com.flashbank.repository.walletRepository;

@Service
public class walletService {

	@Autowired
	private walletRepository walletRepository;

	public String newWallet(Wallet wallet) throws Exception {

		String status;

		try {

			// block of security

			String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			String host_ip = InetAddress.getLocalHost().getHostAddress();

			String host_name = InetAddress.getLocalHost().getHostName();

			String host = date_time + " " + host_name + " " + host_ip;

			wallet.setCreated_in_and_by(host);

			// end block of security

			walletRepository.save(wallet);

			status = "Carteira criada com SUCESSO :D";

		} catch (Exception e) {

			status = "Erro ao criar carteira, tente novamente :(";

		}

		return status;

	}

	public List<Wallet> searchAllWallets() {

		List<Wallet> allWallets = (List<Wallet>) walletRepository.findAll();

		return allWallets;

	}

	public Wallet searchById(Long id) throws Exception {

		try {

			Optional<Wallet> wallet = walletRepository.findById(id);

			return wallet.get();

		} catch (Exception e) {

			return null;

		}

	}

	public String updateWallet(Wallet wallet) throws Exception {

		String status;

		try {

			String date_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			String host_ip = InetAddress.getLocalHost().getHostAddress();

			String host_name = InetAddress.getLocalHost().getHostName();

			String host = date_time + " " + host_name + " " + host_ip;

			wallet.setUpdate_in_and_by(host);

			walletRepository.save(wallet);

			status = "Carteira edita com SUCESSO :D";

		} catch (Exception e) {

			status = "Erro ao editar a carteira, tente novamente :(";

		}
		return status;

	}
}

package com.flashbank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flashbank.model.Account;
import com.flashbank.repository.accountRepository;

@Service
public class ImplementationAccountDetailsService implements UserDetailsService{
	
	@Autowired
	private accountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = accountRepository.findUserByLogin(username);

		if (username == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado :(");
		}

		return new User(account.getDocument(), account.getPassword(), account.getAuthorities());
	}

}

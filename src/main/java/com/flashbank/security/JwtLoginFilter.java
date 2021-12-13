package com.flashbank.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashbank.model.Account;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	// configurando o gerenciador de autenticação.

	protected JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
		
		// obriga a autenticar a url.
		
		super(new AntPathRequestMatcher(url));
		
		// gerenciador de autenticação.
		
		setAuthenticationManager(authenticationManager);
	
		
	}

	// retorna o usuário ao processar a autenticação.

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		// está pegando o token para validar.

		Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);

		// retorna o usuário => e-mail, senha e acesso.

		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(account.getDocument(), account.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		

		new JwtTokenAuthenticationService().addAuthentication(response, authResult.getName());

	}

}

package com.flashbank.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.flashbank.ApplicationContextLoad;
import com.flashbank.model.Account;
import com.flashbank.repository.accountRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JwtTokenAuthenticationService {
	
	private static final long EXPIRATION_TIME = 172800000;
	
	private static final String SECRET = "GJe9CKrR8Qw1159753486123qwerfdsaf45";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, String username) throws IOException {
		
		String JWT = Jwts.builder() 
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();

		String token = TOKEN_PREFIX + " " + JWT;

		response.addHeader(HEADER_STRING, token);
		

		ApplicationContextLoad.getApplicationContext().getBean(accountRepository.class).updateTokenAccount(JWT,
				username);

		liberarCORS(response);

		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {


		String token = request.getHeader(HEADER_STRING);

		try {

			if (token != null) {

				String cleanToken = token.replace(TOKEN_PREFIX, "").trim();

				String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(cleanToken).getBody().getSubject();

				if (user != null) {	
					
					Account account = ApplicationContextLoad.getApplicationContext().getBean(accountRepository.class)
							.findUserByLogin(user);

					if (account != null) {

						if (cleanToken.equalsIgnoreCase(account.getToken())) {

							return new UsernamePasswordAuthenticationToken(account.getDocument(), account.getPassword(),
									account.getAuthorities());
						} 
					}
				}
			}

		} catch (io.jsonwebtoken.ExpiredJwtException e) {

			try {
				response.getOutputStream().println("Token Expirado :(");
			} catch (IOException e1) {
				
			}

		}

		liberarCORS(response);

		return null;

	}

	// CORS policy
	private void liberarCORS(HttpServletResponse response) {

		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}

		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}

		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}

		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}

}

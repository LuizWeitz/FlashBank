package com.flashbank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementationAccountDetailsService ImplementationAccountDetailsService;
	
	// configura as solicitações de acesso as urls.
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		// ativando proteção contra usuários, os quais não está validados por token.
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		// ativando a permissão a página inicial.
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		
		// liberação do CORS
		
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		
		// url de logout, redirecionamento de url, depois que o usuário se desloga.
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		// mapeamento de url logout e inválinda o usuário.
		
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		// filtragem de requisições de login para autenticação.
		
		.and().addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
		
		// filtra demais requisições para verificar a presença do TOKEN JWT no HEADER HTTP.
		
		.addFilterBefore(new JwtApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// codificação da senha do usuário.
		
		auth.userDetailsService(ImplementationAccountDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		
	}
	

}

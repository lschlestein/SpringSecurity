package com.example.SpringSecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean//Bean que é responsável pela liberação de acesso ou não a uma determinada view.
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "home").permitAll() //acesso irrestrito (Sem usuário e Senha) para as view "/" e "/home"
		.anyRequest().authenticated()	//define acesso autenticado para as demais views (Nesse caso "/hello")
		.and()
		.formLogin()
			.loginPage("/login").permitAll() //Configura o acesso a view de login e da acesso irrestrito a ela
			.and()
		.logout()
			.permitAll();
		return http.build();
	}

	@Bean// Bean que cria um usuário em memória para acessar a aplicação via autenticação
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("lucas")
				.password("123")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

}

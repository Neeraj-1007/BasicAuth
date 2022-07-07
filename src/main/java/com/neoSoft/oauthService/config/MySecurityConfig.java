package com.neoSoft.oauthService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//	to disable csrf	
//			.csrf().disable()
//		if don't want to disable than
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeHttpRequests()
//			.antMatchers("/home","/login","/register").permitAll()
			.antMatchers("/public/**").hasRole("LEAD")
			.antMatchers("/users/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

//	with no password encoder
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication().withUser("neeraj").password("neeraj").roles("admin");
//		auth.inMemoryAuthentication().withUser("PoojaShree").password("poojashree").roles("Lead");
//		
//	}
//	@Bean
//	public PasswordEncoder paswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
//	with passwordEncoder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("Neeraj").password(this.paswordEncoder().encode("neeraj")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("PoojaShree").password(this.paswordEncoder().encode("poojashree")).roles("LEAD");
		
	}
	@Bean
	public PasswordEncoder paswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	

}

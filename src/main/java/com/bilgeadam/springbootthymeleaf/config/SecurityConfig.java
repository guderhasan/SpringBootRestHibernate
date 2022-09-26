package com.bilgeadam.springbootthymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/ogretmen/ekle").hasRole("ADMIN");
		http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().and().formLogin().defaultSuccessUrl("/ogretmen");
		return http.build();
	}
}
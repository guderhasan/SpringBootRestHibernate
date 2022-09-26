package com.bilgeadam.springbootthymeleaf.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleaf.model.CustomUser;
import com.bilgeadam.springbootthymeleaf.repo.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	private UserRepository repo;

	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser user;
		user = repo.findByusername(username);
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		builder.password(user.getPassword());
		builder.authorities(user.getRoles());
		return builder.build();
	}

	public boolean save(CustomUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user) != null;
	}
}
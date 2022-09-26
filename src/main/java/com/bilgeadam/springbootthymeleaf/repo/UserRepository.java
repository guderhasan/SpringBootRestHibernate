package com.bilgeadam.springbootthymeleaf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.springbootthymeleaf.model.CustomUser;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<CustomUser, Long>
{
	public CustomUser findByusername(String username);
}
package com.sec.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.demo.entity.user;

public interface userRepo  extends JpaRepository<user, Integer> {
	
	public Optional<user> findByUsername(String username);
	
}
